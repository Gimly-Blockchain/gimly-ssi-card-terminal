package io.gimly.card.api

import com.tangem.TangemSdk
import com.tangem.jvm.init
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SsiCardApiApplication

fun main(args: Array<String>) {
    val sdk = TangemSdk.init(true)
//        sdk.createWallet()
    runApplication<SsiCardApiApplication>(*args)
}


