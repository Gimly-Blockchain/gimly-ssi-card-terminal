package io.gimly.tangem.ssicardapi

import com.tangem.TangemSdk
import com.tangem.jvm.init
import io.gimly.generated.card.model.WalletResults
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SsiCardApiApplication

fun main(args: Array<String>) {
    val sdk = TangemSdk.init(true)
//        sdk.createWallet()
    runApplication<SsiCardApiApplication>(*args)
}


@RestController
class CardController {
    @GetMapping
    fun listWallets(): WalletResults {

        return WalletResults(id = "")
    }
}
