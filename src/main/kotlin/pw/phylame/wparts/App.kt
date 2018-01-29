package pw.phylame.wparts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
