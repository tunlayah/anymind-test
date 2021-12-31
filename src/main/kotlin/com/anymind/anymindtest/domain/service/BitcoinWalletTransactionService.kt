package com.anymind.anymindtest.domain.service

import com.anymind.anymindtest.domain.entity.BitcoinWalletTransaction

interface BitcoinWalletTransactionService {
    fun save(amount: Double): BitcoinWalletTransaction
}
