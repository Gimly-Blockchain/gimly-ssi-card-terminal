package io.gimly.card.api.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tangem.Message
import com.tangem.TangemSdk
import com.tangem.common.CompletionResult
import com.tangem.common.card.Card
import com.tangem.common.card.CardWallet
import com.tangem.common.card.EllipticCurve
import com.tangem.common.card.SigningMethod
import com.tangem.common.extensions.toHexString
import com.tangem.jvm.init
import com.tangem.operations.files.ReadFilesResponse
import com.tangem.operations.wallet.CreateWalletResponse
import io.gimly.generated.card.model.*
import org.springframework.stereotype.Service
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.streams.toList


@Service
class CardService(val objectMapper: ObjectMapper) {

    private val sdk = (TangemSdk.init(
        verbose = true,
//        indexOfTerminal = 0 // can be omitted when only one NFC reader is connected to the computer, we probably want to make a setting here
    ))!!

    init {
        sdk ?: throw Exception("SDK not initialized")
    }


    suspend fun scanCard(): CardInfoResult {
        sdk.startSession { session, error ->  }
        return cardCoroutine().toCardInfoResult()
    }

    suspend fun listKeys(cardId: String?): KeyResults {
        val card = cardCoroutine(cardId)
        return KeyResults(id = card.cardId, propertyKeys = card.wallets.stream().map { it.toKeyInfo() }.toList())
    }

    suspend fun getKeyById(cardId: String?, keyId: String): KeyInfo? {
        val wallet = cardCoroutine(cardId, keyId).wallet(keyId.toPublicKey())
        return wallet?.toKeyInfo()
    }

    suspend fun deleteKeyById(cardId: String, keyId: String) {
        return deleteKeyCoroutine(cardId, keyId)
    }

    suspend fun createKey(cardId: String, createKeyRequest: CreateKeyRequest): KeyInfo? {
        return createKeyCoroutine(
            cardId,
            isReusable = createKeyRequest.unrevokeable ?: false,
            curve = createKeyRequest.curve ?: Curve.ed25519
        ).toKeyInfo()
    }

    suspend fun sign(cardId: String, keyId: String, signRequest: SignRequest): SignResponse? {
        val signResult = signCoroutine(cardId, keyId, signRequest.inputs.stream().map { it.data.toByteArray() }.toList())
        var index = 0
        val signatures = signRequest.inputs.map { signInput ->
            SignOutputFromInput(signInput, SignOutput(signResult.signatures[index++].toHexString(), encoding = OutputEncoding.hex))
        }
        return SignResponse(signatures = signatures)
    }

    suspend fun listStoredVCs(cardId: String?): StoredCredentialsResponse {
        val credentials = listFilesCoroutine(cardId).files.mapNotNull {
            try {
                objectMapper.readValue<VerifiableCredential>(it.fileData)
            } catch (e: Exception) {
                null
            }
        }.toList()
        return StoredCredentialsResponse(credentials)
    }

    suspend fun getStoredVC(cardId: String?, credentialId: String): VerifiableCredential? {
        return listStoredVCs(cardId).credentials?.first { credentialId == it.id }
    }

    suspend fun deleteStoredVC(cardId: String?, credentialId: String): VerifiableCredential? {
        return listFilesCoroutine(cardId).files.mapIndexed { index, file ->
            try {
                val credential = objectMapper.readValue<VerifiableCredential>(file.fileData)
                if (credentialId == credential.id) {
                    // TODO: Make coroutine
                    sdk.deleteFiles(arrayListOf(index), cardId, null, callback = {})
                    return credential
                }
                return null
            } catch (e: Exception) {
                return null
            }

        }.first()

    }


    private suspend fun listFilesCoroutine(cardId: String? = null): ReadFilesResponse {
        return suspendCoroutine { continuation ->
            val message = Message("Gimly ID Card needed", "Please tap your Gimly ID Card to the reader to retrieve Credentials")
            sdk.readFiles(true, null, cardId, message) { result ->
                when (result) {
                    is CompletionResult.Success -> continuation.resumeWith(Result.success(result.data))
                    is CompletionResult.Failure -> continuation.resumeWith(Result.failure(Exception(result.error.customMessage)))
                }
            }
        }
    }

    private suspend fun signCoroutine(cardId: String, keyId: String, inputs: List<ByteArray>): com.tangem.operations.sign.SignResponse {
        val typedArray = inputs.toTypedArray()
        return suspendCoroutine { continuation ->
            val message = Message("Gimly ID Card needed", "Please tap your Gimly ID Card to the reader to sign")
            // FIXME: 02/07/2021 We need to retrieve the public key at all times first here. So add DID kid and index support!
            val key = if (keyId.startsWith("z")) {
                keyId.drop(1)
            } else {
                keyId
            }
            sdk.sign(hashes = typedArray, key.toByteArray(), cardId, initialMessage = message) { result ->
                when (result) {
                    is CompletionResult.Success -> continuation.resumeWith(Result.success(result.data))
                    is CompletionResult.Failure -> continuation.resumeWith(Result.failure(Exception(result.error.customMessage)))
                }
            }
        }
    }

    private suspend fun createKeyCoroutine(cardId: String, isReusable: Boolean? = false, curve: Curve): CreateWalletResponse {
        return suspendCoroutine { continuation ->
            val message = Message("Gimly ID Card needed", "Please tap your Gimly ID Card to the reader to create a new Key")
            /*val walletConfig = WalletConfig(
                isReusable,
                curveId = EllipticCurve.valueOf(curve.value),
                prohibitPurgeWallet = false,
                signingMethods = SigningMethod.SignRaw
            )*/
            sdk.createWallet(curve = EllipticCurve.valueOf(curve.value), cardId, message) { result ->
                when (result) {
                    is CompletionResult.Success -> continuation.resumeWith(Result.success(result.data))
                    is CompletionResult.Failure -> continuation.resumeWith(Result.failure(Exception(result.error.customMessage)))
                }
            }
        }
    }

    private suspend fun deleteKeyCoroutine(cardId: String, keyId: String): Unit {
        return suspendCoroutine { continuation ->
            val message = Message("Key Deactivation", "Please tap your Gimly ID Card to the reader to deactivate a Key")
            sdk.purgeWallet(keyId.toPublicKey(), cardId, message) { result ->
                when (result) {
                    is CompletionResult.Success -> continuation.resume(Unit)
                    is CompletionResult.Failure -> continuation.resumeWith(Result.failure(Exception(result.error.customMessage)))
                }
            }
        }
    }

    private suspend fun cardCoroutine(cardId: String? = null, keyId: String? = null): Card {
        return suspendCoroutine { continuation ->
            val message = Message("Gimly ID Card needed", "Please tap your Gimly ID Card to the reader")
            sdk.scanCard(message) { result ->
                when (result) {
                    is CompletionResult.Success -> continuation.resumeWith(Result.success(result.data))
                    is CompletionResult.Failure -> continuation.resumeWith(Result.failure(Exception(result.error.customMessage)))
                }
            }
        }
    }
}
/*

fun CardStatus.toKeyStatus() = when (code) {
    2 -> KeyStatus.active
    3 -> KeyStatus.inActive
    else -> KeyStatus.unAvailable
}*/

fun CardWallet.Status.toKeyStatus() = when (code) {
    2 -> KeyStatus.active
    3 -> KeyStatus.inActive
    else -> KeyStatus.unAvailable
}


// TODO: we need to inspect the curve and adjust the multibase accordingingly
fun CreateWalletResponse.toKeyInfo() = KeyInfo(index = wallet.index, publicKeyMultibase = "z${wallet.publicKey}"/*, status = CardWallet.Status.toKeyStatus()*/)

// TODO: we need to inspect the curve and adjust the multibase accordingingly
fun CardWallet.toKeyInfo() = KeyInfo(index = index, publicKeyMultibase = "z${publicKey}"/*, status = status.toKeyStatus()*/)

/*fun PurgeWalletResponse.toKeyInfo(publicKeyMultibase: String? = null) =
    KeyInfo(publicKeyMultibase, index = walletIndex.toInt(), status = status.toKeyStatus())*/
/*
fun WalletIndex.toInt() = when (this) {
    is WalletIndex.Index -> this.index
    else -> -1
}*/


fun Card.toCardInfoResult() = CardInfoResult(cardId = cardId, batchId = batchId)

fun String.toPublicKey() =
    if (this.length >= 16) {
        if (this.startsWith("z")) {
            this.drop(1).decodeHex()
        } else {
            this.decodeHex()
        }
    } else {
        throw Exception("Cannot get wallet by invalid key $this")
    }

fun String.decodeHex(): ByteArray {
    check(length % 2 == 0) { "Hex bust be of even length" }

    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}
