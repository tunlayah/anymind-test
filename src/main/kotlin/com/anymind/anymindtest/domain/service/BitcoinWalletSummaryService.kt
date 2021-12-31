package com.anymind.anymindtest.domain.service

import com.anymind.anymindtest.domain.entity.BitcoinWalletSummary
import java.time.ZonedDateTime

interface BitcoinWalletSummaryService {
    fun save(amount: Double, transactionDate: ZonedDateTime): BitcoinWalletSummary
}
