package coder.greyfei.kotlinlang.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by GreyFei on 2025/5/10
 */

/**
 * 日志开头带时间戳
 */
fun printlnT(message: Any?) {
    val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault()).format(Date())
    println("[$time] $message")
}