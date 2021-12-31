package com.anymind.anymindtest.data.service

import com.anymind.anymindtest.data.entity.BitcoinWalletSummaryHibernateImpl
import com.anymind.anymindtest.data.repository.BitcoinWalletSummaryRepository
import com.anymind.anymindtest.domain.entity.BitcoinWalletSummary
import com.anymind.anymindtest.domain.service.BitcoinWalletSummaryService
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BitcoinWalletSummaryHibernateServiceImpl(
    private val bitcoinWalletSummaryRepository: BitcoinWalletSummaryRepository
) : BitcoinWalletSummaryService {


    override fun save(amount: Double, transactionDate: ZonedDateTime): BitcoinWalletSummary {
        val queryTransactionDate = transactionDate.truncatedTo(ChronoUnit.HOURS)
        val bitcoinWalletSummary = getByDatetime(queryTransactionDate)?.let { summary ->
            summary.apply {
                this.amount = this.amount?.let { it + amount }
            }
        }?: run {
            BitcoinWalletSummaryHibernateImpl().apply {
                this.amount = amount
                this.datetime = queryTransactionDate
            }
        }
        return save(bitcoinWalletSummary)
    }

    override fun getSummary(startDate: ZonedDateTime, endDate: ZonedDateTime, pageable: Pageable): Page<BitcoinWalletSummary> {
        return bitcoinWalletSummaryRepository.findByDatetimeGreaterThanEqualAndDatetimeLessThanEqual(startDate, endDate, pageable) as Page<BitcoinWalletSummary>
    }

    @Transactional(readOnly = true)
    fun getByDatetime(transactionDate: ZonedDateTime): BitcoinWalletSummary? {
        return bitcoinWalletSummaryRepository.findFirstByDatetime(transactionDate)
    }

    @Transactional
    fun save(bitcoinWalletSummary: BitcoinWalletSummary): BitcoinWalletSummary {
        return bitcoinWalletSummaryRepository.save(bitcoinWalletSummary as BitcoinWalletSummaryHibernateImpl)
    }
}
