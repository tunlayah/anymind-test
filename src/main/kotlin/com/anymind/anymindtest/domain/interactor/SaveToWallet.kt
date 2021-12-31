package com.anymind.anymindtest.domain.interactor

import com.anymind.anymindtest.domain.service.BitcoinWalletTransactionService
import com.anymind.anymindtest.resource.rest.dto.BitcoinWalletTransactionSaveDTO
import org.springframework.stereotype.Component

@Component
class SaveToWallet(
    private val bitcoinWalletTransactionService: BitcoinWalletTransactionService
) {
    fun execute(bitcoinWalletTransactionSaveDTO: BitcoinWalletTransactionSaveDTO) {
        val amount = requireNotNull(bitcoinWalletTransactionSaveDTO.amount) { "Save amount cannot be null." }
        require(amount > 0) { "Amount must greater than 0." }
        bitcoinWalletTransactionService.save(amount)
    }
}
