package com.anymind.anymindtest.data.service

import com.anymind.anymindtest.data.entity.BitcoinWalletTransactionHibernateImpl
import com.anymind.anymindtest.data.repository.BitcoinWalletTransactionRepository
import com.anymind.anymindtest.domain.entity.BitcoinWalletTransaction
import com.anymind.anymindtest.domain.service.BitcoinWalletTransactionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BitcoinWalletTransactionHibernateServiceImpl(
    private val bitcoinWalletTransactionRepository: BitcoinWalletTransactionRepository
) : BitcoinWalletTransactionService {


    override fun save(amount: Double): BitcoinWalletTransaction {
        val bitcoinWalletTransaction = BitcoinWalletTransactionHibernateImpl().apply {
            this.amount = amount
        }
        return save(bitcoinWalletTransaction)
    }

    @Transactional
    fun save(bitcoinWalletTransaction: BitcoinWalletTransaction): BitcoinWalletTransaction {
        return bitcoinWalletTransactionRepository.save(bitcoinWalletTransaction as BitcoinWalletTransactionHibernateImpl)
    }
}
