package io.gimly.card.api.controllers

import io.gimly.card.api.services.CardService
import io.gimly.generated.card.model.CardInfoResult
import io.gimly.generated.card.model.WalletInfo
import io.gimly.generated.card.model.WalletResults
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CardController(val cardService: CardService) {


    @GetMapping("/")
    suspend fun scanCard(): CardInfoResult {
        return cardService.scanCard()
    }

    @GetMapping("/wallets")
    suspend fun listWallets(@RequestParam cardId: String?): WalletResults {
        return cardService.listWallets(cardId)
    }

    @GetMapping("/wallets/{walletId}")
    suspend fun getWalletById(@RequestParam cardId: String?, @PathVariable walletId: String): WalletInfo {
        return cardService.getWalletById(cardId, walletId) ?: throw Exception("Could not retrieve wallet with id $walletId for card $cardId")
    }
}
