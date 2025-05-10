package coder.greyfei.kotlinlang.coroutines

import coder.greyfei.kotlinlang.utils.printlnT
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Created by GreyFei on 2025/5/10
 */
fun main() {
    runBlocking {
        printlnT("runBlocking start")
        delay(2000)
        printlnT("runBlocking end")
    }
}