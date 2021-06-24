package io.gimly.card.api.services

import com.tangem.Message
import com.tangem.TangemSdk
import com.tangem.commands.common.card.Card
import com.tangem.commands.common.card.CardStatus
import com.tangem.commands.common.card.EllipticCurve
import com.tangem.commands.common.card.masks.SigningMethod
import com.tangem.commands.wallet.*
import com.tangem.common.CompletionResult
import com.tangem.jvm.init
import io.gimly.generated.card.model.*
import org.springframework.stereotype.Service
import kotlin.coroutines.suspendCoroutine
import kotlin.streams.toList


@Service
class CardService {

    private val sdk = (TangemSdk.init(
        verbose = true,
        indexOfTerminal = 0 // can be omitted when only one NFC reader is connected to the computer, we probably want to make a setting here
    ))!!

    init {
        sdk ?: throw Exception("SDK not initialized")
    }


    suspend fun scanCard(): CardInfoResult {
        return cardCoroutine().toCardInfoResult()
    }

    suspend fun listKeys(cardId: String?): KeyResults {
        val card = cardCoroutine(cardId)
        return KeyResults(id = card.cardId, keys = card.wallets.stream().map { it.toKeyInfo() }.toList())
    }

    suspend fun getKeyById(cardId: String?, keyId: String): KeyInfo? {
        val wallet = cardCoroutine(cardId, keyId).wallet(keyId.toWalletIndex())
        return wallet?.toKeyInfo()
    }

    suspend fun deleteKeyById(cardId: String?, keyId: String): KeyInfo? {
        return deleteKeyCoroutine(cardId, keyId).toKeyInfo()
    }

    suspend fun createKey(cardId: String?, createKeyRequest: CreateKeyRequest): KeyInfo? {
        return createKeyCoroutine(
            cardId,
            isReusable = createKeyRequest.unrevokeable ?: false,
            curve = createKeyRequest.curve ?: Curve.ed25519
        ).toKeyInfo()
    }


    private suspend fun createKeyCoroutine(cardId: String? = null, isReusable: Boolean? = false, curve: Curve): CreateWalletResponse {
        return suspendCoroutine { continuation ->
            val message = Message("Gimly ID Card needed", "Please tap your Gimly ID Card to the reader to create a new Key")
            val walletConfig = WalletConfig(
                isReusable,
                curveId = EllipticCurve.valueOf(curve.value),
                prohibitPurgeWallet = false,
                signingMethods = SigningMethod.SignRaw
            )
            sdk.createWallet(walletConfig, cardId, message) { result ->
                when (result) {
                    is CompletionResult.Success -> continuation.resumeWith(Result.success(result.data))
                    is CompletionResult.Failure -> continuation.resumeWith(Result.failure(Exception(result.error.customMessage)))
                }
            }
        }
    }

    private suspend fun deleteKeyCoroutine(cardId: String? = null, keyId: String): PurgeWalletResponse {
        return suspendCoroutine { continuation ->
            val message = Message("Key Deactivation", "Please tap your Gimly ID Card to the reader to deactivate a Key")
            sdk.purgeWallet(keyId.toWalletIndex(), cardId, message) { result ->
                when (result) {
                    is CompletionResult.Success -> continuation.resumeWith(Result.success(result.data))
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




fun CardStatus.toKeyStatus() = when (code) {
    2 -> KeyStatus.active
    3 -> KeyStatus.inActive
    else -> KeyStatus.unAvailable
}

fun WalletStatus.toKeyStatus() = when (code) {
    2 -> KeyStatus.active
    3 -> KeyStatus.inActive
    else -> KeyStatus.unAvailable
}


// TODO: we need to inspect the curve and adjust the multibase accordingingly
fun CreateWalletResponse.toKeyInfo() = KeyInfo(index = walletIndex, publicKeyMultibase = "z$walletPublicKey", status = status.toKeyStatus())

// TODO: we need to inspect the curve and adjust the multibase accordingingly
fun CardWallet.toKeyInfo() = KeyInfo(index = index, publicKeyMultibase = "z${publicKey}", status = status.toKeyStatus())

fun PurgeWalletResponse.toKeyInfo(publicKeyMultibase : String? = null) = KeyInfo(publicKeyMultibase, index = walletIndex.toInt(), status = status.toKeyStatus())

fun WalletIndex.toInt() = when(this) {
    is WalletIndex.Index -> this.index
    else -> -1
}



fun Card.toCardInfoResult() = CardInfoResult(cardId = cardId, batchId = cardData?.batchId)

fun String.toWalletIndex() =
    if (this.length <= 3) {
        WalletIndex.Index(this.toInt())
    } else if (this.length >= 16) {
        if (this.startsWith("z")) {
            WalletIndex.PublicKey(this.drop(1).decodeHex())
        } else {
            WalletIndex.PublicKey(this.decodeHex())
        }
    } else {
        throw Exception("Cannot get wallet by invalid index $this")
    }

fun String.decodeHex(): ByteArray {
    check(length % 2 == 0) { "Hex bust be of even length" }

    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}
