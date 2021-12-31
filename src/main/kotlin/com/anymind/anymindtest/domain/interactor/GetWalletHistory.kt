package com.anymind.anymindtest.domain.interactor

import com.anymind.anymindtest.domain.service.BitcoinWalletSummaryService
import com.anymind.anymindtest.resource.rest.dto.BitcoinWalletSummaryDTO
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class GetWalletHistory(
    private val bitcoinWalletSummaryService: BitcoinWalletSummaryService
) {
    fun execute(startDateInput: String, endDateInput: String, pageable: Pageable): List<BitcoinWalletSummaryDTO> {
        val startDate = convertStringToZonedDateTime(startDateInput)
        val endDate = convertStringToZonedDateTime(endDateInput)
        require(endDate >= startDate) { "Start date must be earlier than end date." }
        val summary = bitcoinWalletSummaryService.getSummary(startDate, endDate, pageable)
        return summary.content.mapNotNull {
            BitcoinWalletSummaryDTO().apply {
                this.datetime = it.datetime?.let { convertZonedDateTimeToString(it) }
                this.amount = it.amount
            }
        }
    }

    private fun convertStringToZonedDateTime(input: String): ZonedDateTime {
        return ZonedDateTime.parse(input)
    }

    private fun convertZonedDateTimeToString(input: ZonedDateTime): String {
        val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        return  dateTimeFormat.format(ZonedDateTime.ofInstant(input.toInstant(), ZoneOffset.systemDefault()))
    }
}
