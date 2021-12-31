package com.anymind.anymindtest.data.repository

import com.anymind.anymindtest.data.entity.BitcoinWalletSummaryHibernateImpl
import java.time.ZonedDateTime
import org.springframework.data.jpa.repository.JpaRepository

interface BitcoinWalletSummaryRepository : JpaRepository<BitcoinWalletSummaryHibernateImpl, Long> {
    fun findFirstByDatetime(datetime: ZonedDateTime): BitcoinWalletSummaryHibernateImpl?
}
