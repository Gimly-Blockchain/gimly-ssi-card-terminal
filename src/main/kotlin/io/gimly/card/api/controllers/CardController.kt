package io.gimly.card.api.controllers

import io.gimly.card.api.services.CardService
import io.gimly.generated.card.model.*
import org.springframework.web.bind.annotation.*

@RestController
class CardController(val cardService: CardService) {

    @GetMapping("/")
    suspend fun scanCard(): CardInfoResult {
        return cardService.scanCard()
    }

    @GetMapping("/keys")
    suspend fun listKeys(@RequestParam cardId: String?): KeyResults {
        return cardService.listKeys(cardId)
    }

    @PostMapping("/keys")
    suspend fun createKey(@RequestParam cardId: String?, @RequestBody createKeyRequest: CreateKeyRequest): KeyInfo {
        return cardService.createKey(cardId, createKeyRequest) ?: throw Exception("Could not create key for card $cardId")
    }

    @GetMapping("/keys/{keyId}")
    suspend fun getKeyById(@RequestParam cardId: String?, @PathVariable keyId: String): KeyInfo {
        return cardService.getKeyById(cardId, keyId) ?: throw Exception("Could not retrieve key with id $keyId for card $cardId")
    }

    @DeleteMapping("/keys/{keyId}")
    suspend fun deleteKeyById(@RequestParam cardId: String?, @PathVariable keyId: String): KeyInfo {
        return cardService.deleteKeyById(cardId, keyId) ?: throw Exception("Could not delete key with id $keyId for card $cardId")
    }

    @PutMapping("/keys/{keyId}/signatures")
    suspend fun signUsingKey(@RequestParam cardId: String?, @PathVariable keyId: String, @RequestBody signRequest: SignRequest): SignResponse {
        return cardService.sign(cardId, keyId, signRequest) ?: throw Exception("Could not sign using key $keyId for card $cardId")
    }

    @GetMapping("/storage/credentials")
    suspend fun listStoredVCs(@RequestParam cardId: String?): StoredCredentialsResponse {
        return cardService.listStoredVCs(cardId)
    }

    @GetMapping("/storage/credentials/{credentialId}")
    suspend fun getStoredVC(@RequestParam cardId: String?, @PathVariable credentialId: String): VerifiableCredential? {
        return cardService.getStoredVC(cardId, credentialId)
    }

    @DeleteMapping("/storage/credentials/{credentialId}")
    suspend fun deleteStoredVCs(@RequestParam cardId: String?, @PathVariable credentialId: String): VerifiableCredential? {
        return cardService.deleteStoredVC(cardId, credentialId)
    }
}
