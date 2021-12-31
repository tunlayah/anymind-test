package com.anymind.anymindtest.resource.rest

import com.anymind.anymindtest.domain.interactor.SaveToWallet
import com.anymind.anymindtest.resource.rest.dto.BitcoinWalletTransactionSaveDTO
import com.anymind.anymindtest.resource.rest.dto.SuccessDTO
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class BitcoinWalletController(
    private val saveToWallet: SaveToWallet
) {

    @PostMapping("/bitcoin-wallet", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveToWallet(
        @RequestBody bitcoinWalletTransactionSaveDTO: BitcoinWalletTransactionSaveDTO
    ): ResponseEntity<SuccessDTO> {
        saveToWallet.execute(bitcoinWalletTransactionSaveDTO)
        return ResponseEntity(SuccessDTO(), HttpStatus.CREATED)
    }
}