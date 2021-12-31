package com.anymind.anymindtest.domain.entity

import java.time.ZonedDateTime

interface BitcoinWalletTransaction {
    var id: Long?
    var datetime: ZonedDateTime?
    var amount: Double?
}
