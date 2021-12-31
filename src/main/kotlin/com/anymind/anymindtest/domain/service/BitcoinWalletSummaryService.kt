package com.anymind.anymindtest.domain.service

import com.anymind.anymindtest.domain.entity.BitcoinWalletSummary
import java.time.ZonedDateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BitcoinWalletSummaryService {
    fun save(amount: Double, transactionDate: ZonedDateTime): BitcoinWalletSummary
    fun getSummary(startDate: ZonedDateTime, endDate: ZonedDateTime, pageable: Pageable): Page<BitcoinWalletSummary>
}
