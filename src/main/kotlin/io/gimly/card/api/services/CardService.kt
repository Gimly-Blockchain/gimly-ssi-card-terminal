package io.gimly.card.api.services

import com.tangem.Message
import com.tangem.TangemSdk
import com.tangem.commands.common.card.Card
import com.tangem.commands.wallet.CardWallet
import com.tangem.commands.wallet.WalletIndex
import com.tangem.common.CompletionResult
import com.tangem.jvm.init
import io.gimly.generated.card.model.CardInfoResult
import io.gimly.generated.card.model.WalletInfo
import io.gimly.generated.card.model.WalletResults
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
        return card().toCardInfoResult()
    }

    suspend fun listWallets(cardId: String?): WalletResults {
        val card = card(cardId)
        return WalletResults(id = card.cardId, wallets = card.wallets.stream().map { it.toWalletInfo() }.toList())
    }

    suspend fun getWalletById(cardId: String?, walletId: String): WalletInfo? {
        val wallet = card(cardId, walletId).wallet(toWalletIndex(walletId))
        return wallet?.toWalletInfo()
    }

    private fun toWalletIndex(walletId: String): WalletIndex {
        return if (walletId.length <= 3) {
            WalletIndex.Index(walletId.toInt())
        } else if (walletId.length >= 16) {
            if (walletId.startsWith("z")) {
                WalletIndex.PublicKey(walletId.drop(1).decodeHex())
            } else {
                WalletIndex.PublicKey(walletId.decodeHex())
            }
        } else {
            throw Exception("Cannot get wallet by invalid index $walletId")
        }
    }

    private suspend fun card(cardId: String? = null, walletId: String? = null): Card {
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

// TODO: we need to inspect the curve and adjust the multibase accordingingly
fun CardWallet.toWalletInfo() = WalletInfo(index = index, publicKeyMultibase = "z${publicKey}")

fun Card.toCardInfoResult() = CardInfoResult(cardId = cardId, batchId = cardData?.batchId)


fun String.decodeHex(): ByteArray {
    check(length % 2 == 0) { "Hex bust be of even length" }

    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}
