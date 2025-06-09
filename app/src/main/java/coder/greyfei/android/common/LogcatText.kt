package coder.greyfei.android.common

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by GreyFei on 2025/5/11
 */


class LogcatTextContainer(private val filterTag: String = "") {

    private var isReading = true
    private var logcatText: MutableState<String> = mutableStateOf("")

    fun startLogcat() {
        isReading = true
        Thread {
            val cmd = "logcat --pid=${android.os.Process.myPid()}"
            val process = Runtime.getRuntime().exec(cmd)
            BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
                while (isReading) {
                    reader.readLine()?.let { line ->
                        if (line.contains(filterTag)) {
                            logcatText.value = "$line\n" + logcatText.value
                        }
                    }
                }
            }
        }.start()
    }

    fun stopLogcat() {
        isReading = false
        logcatText.value = ""
    }

    @Composable
    fun LogcatText(showLog: MutableState<Boolean>) {
        if (showLog.value) {
            Text(
                text = logcatText.value,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
        }
    }

}