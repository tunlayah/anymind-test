package com.anymind.anymindtest.domain.interactor

import com.anymind.anymindtest.domain.service.BitcoinWalletSummaryService
import com.anymind.anymindtest.domain.service.BitcoinWalletTransactionService
import com.anymind.anymindtest.resource.rest.dto.BitcoinWalletTransactionSaveDTO
import java.time.ZonedDateTime
import org.springframework.stereotype.Component

@Component
class SaveToWallet(
    private val bitcoinWalletTransactionService: BitcoinWalletTransactionService,
    private val bitcoinWalletSummaryService: BitcoinWalletSummaryService
) {
    fun execute(bitcoinWalletTransactionSaveDTO: BitcoinWalletTransactionSaveDTO) {
        val amount = requireNotNull(bitcoinWalletTransactionSaveDTO.amount) { "Save amount cannot be null." }
        require(amount > 0) { "Amount must greater than 0." }
        val transaction = bitcoinWalletTransactionService.save(amount)
        bitcoinWalletSummaryService.save(amount, transaction.datetime ?: ZonedDateTime.now())
    }
}
