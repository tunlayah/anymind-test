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
        val latestSummary = getLatestSummary()
        return when {
            latestSummary == null -> {
                val bitcoinWalletSummary = BitcoinWalletSummaryHibernateImpl().apply {
                    this.amount = amount
                    this.datetime = queryTransactionDate
                }
                save(bitcoinWalletSummary)
            }
            latestSummary.datetime == queryTransactionDate -> {
                latestSummary.apply {
                    this.amount = (this.amount ?: 0.0) + amount
                }
                save(latestSummary)
            }
            else -> {
                val createSummaryFromDatetime = requireNotNull(latestSummary.datetime?.plusHours(1))
                val createSummaryToDatetime = requireNotNull(queryTransactionDate.minusHours(1))
                val latestAmount = latestSummary.amount ?: 0.0
                createBitcoinWalletSummary(createSummaryFromDatetime, createSummaryToDatetime, latestAmount)
                val bitcoinWalletSummary = BitcoinWalletSummaryHibernateImpl().apply {
                    this.amount = latestAmount + amount
                    this.datetime = queryTransactionDate
                }
                save(bitcoinWalletSummary)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    @Transactional(readOnly = true)
    override fun getSummary(startDate: ZonedDateTime, endDate: ZonedDateTime, pageable: Pageable): Page<BitcoinWalletSummary> {
        return bitcoinWalletSummaryRepository.findByDatetimeGreaterThanEqualAndDatetimeLessThanEqual(startDate, endDate, pageable) as Page<BitcoinWalletSummary>
    }

    @Transactional(readOnly = true)
    fun getLatestSummary(): BitcoinWalletSummary? {
        return bitcoinWalletSummaryRepository.findFirstByOrderByDatetimeDesc()
    }

    @Transactional
    fun save(bitcoinWalletSummary: BitcoinWalletSummary): BitcoinWalletSummary {
        return bitcoinWalletSummaryRepository.save(bitcoinWalletSummary as BitcoinWalletSummaryHibernateImpl)
    }

    @Suppress("UNCHECKED_CAST")
    @Transactional
    fun saveAll(bitcoinWalletSummaries: List<BitcoinWalletSummary>): List<BitcoinWalletSummary> {
        return bitcoinWalletSummaryRepository.saveAll(bitcoinWalletSummaries as List<BitcoinWalletSummaryHibernateImpl>)
    }

    private fun createBitcoinWalletSummary(fromDatetime: ZonedDateTime, toDatetime: ZonedDateTime, amount: Double) {
        var countingDatetime = fromDatetime
        val summaries = mutableListOf<BitcoinWalletSummary>()
        while (countingDatetime <= toDatetime) {
            val bitcoinWalletSummary = BitcoinWalletSummaryHibernateImpl().apply {
                this.amount = amount
                this.datetime = countingDatetime
            }
            summaries.add(bitcoinWalletSummary)
            countingDatetime = countingDatetime.plusHours(1)
        }
        if (summaries.isNotEmpty()) {
            saveAll(summaries)
        }
    }
}
