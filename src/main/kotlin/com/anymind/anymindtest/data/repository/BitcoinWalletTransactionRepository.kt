package com.anymind.anymindtest.data.repository

import com.anymind.anymindtest.data.entity.BitcoinWalletTransactionHibernateImpl
import org.springframework.data.jpa.repository.JpaRepository

interface BitcoinWalletTransactionRepository : JpaRepository<BitcoinWalletTransactionHibernateImpl, Long>
