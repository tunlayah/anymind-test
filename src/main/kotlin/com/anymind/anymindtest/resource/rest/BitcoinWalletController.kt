package com.anymind.anymindtest.resource.rest

import com.anymind.anymindtest.domain.interactor.GetWalletHistory
import com.anymind.anymindtest.domain.interactor.SaveToWallet
import com.anymind.anymindtest.resource.rest.dto.BitcoinWalletSummaryDTO
import com.anymind.anymindtest.resource.rest.dto.BitcoinWalletTransactionSaveDTO
import com.anymind.anymindtest.resource.rest.dto.SuccessDTO
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@RestController
@RequestMapping("api")
class BitcoinWalletController(
    private val saveToWallet: SaveToWallet,
    private val getWalletHistory: GetWalletHistory
) {

    @PostMapping("/bitcoin-wallet", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveToWallet(
        @RequestBody bitcoinWalletTransactionSaveDTO: BitcoinWalletTransactionSaveDTO
    ): ResponseEntity<SuccessDTO> {
        saveToWallet.execute(bitcoinWalletTransactionSaveDTO)
        return ResponseEntity(SuccessDTO(), HttpStatus.CREATED)
    }

    @ApiImplicitParams(
        ApiImplicitParam(name = "sort", value = "Field to sort, {asc, desc}", defaultValue = "datetime,desc", paramType = "query", dataType = "string"),
        ApiImplicitParam(name = "size", value = "Page Size", defaultValue = "20", paramType = "query", dataType = "int"),
        ApiImplicitParam(name = "page", value = "Page number", defaultValue = "0", paramType = "query", dataType = "int")
    )
    @GetMapping("/bitcoin-wallet/history", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getWalletHistory(
        @RequestParam(value = "startDatetime", required = true) startDatetime: String,
        @RequestParam(value = "endDatetime", required = true) endDatetime: String,
        @ApiIgnore pageable: Pageable
    ): ResponseEntity<List<BitcoinWalletSummaryDTO>> {
        val result = getWalletHistory.execute(startDatetime, endDatetime, pageable)
        return ResponseEntity(result, HttpStatus.OK)
    }
}