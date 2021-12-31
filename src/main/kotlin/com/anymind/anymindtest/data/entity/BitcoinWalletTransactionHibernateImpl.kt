package com.anymind.anymindtest.data.entity

import com.anymind.anymindtest.domain.entity.BitcoinWalletTransaction
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import org.springframework.data.annotation.CreatedDate

@Entity
@Table(name = "bitcoin_wallet_transaction")
class BitcoinWalletTransactionHibernateImpl : BitcoinWalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    override var id: Long? = null

    @CreatedDate
    @Column(name = "datetime")
    override var datetime: ZonedDateTime? = null

    @Column(name = "amount")
    override var amount: Double? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BitcoinWalletTransactionHibernateImpl) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
