package io.gimly.card.api

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SsiCardApiApplication

fun main(args: Array<String>) {
//    val sdk = TangemSdk.init(true)
//        sdk.createWallet()
    runApplication<SsiCardApiApplication>(*args)
}


