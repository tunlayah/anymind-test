package com.anymind.anymindtest.data.repository

import com.anymind.anymindtest.data.entity.BitcoinWalletSummaryHibernateImpl
import java.time.ZonedDateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BitcoinWalletSummaryRepository : JpaRepository<BitcoinWalletSummaryHibernateImpl, Long> {
    fun findFirstByOrderByDatetimeDesc(): BitcoinWalletSummaryHibernateImpl?
    fun findByDatetimeGreaterThanEqualAndDatetimeLessThanEqual(startDatetime: ZonedDateTime, endDatetime: ZonedDateTime, pageable: Pageable): Page<BitcoinWalletSummaryHibernateImpl>
}
