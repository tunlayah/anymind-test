package com.anymind.anymindtest.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class BitcoinWalletController {

    @GetMapping("/bitcoin/wallet")
    fun saveWallet(): String {
        return "test"
    }
}