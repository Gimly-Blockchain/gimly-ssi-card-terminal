package io.gimly.card.api.controllers

import io.gimly.card.api.services.CardService
import io.gimly.generated.card.model.CardInfoResult
import io.gimly.generated.card.model.WalletResults
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CardController(val cardService: CardService) {


    @GetMapping
    suspend fun scanCard() : CardInfoResult {
        return cardService.scanCard()
    }

    @GetMapping
    suspend fun listWallets(): WalletResults {
        return cardService.listWallets()
    }

    @GetMapping
    suspend fun getWalletByIndex(index : int): WalletResults {
        return cardService.listWallets()
    }
}
