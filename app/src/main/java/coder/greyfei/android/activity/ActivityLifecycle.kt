package coder.greyfei.android.activity

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import coder.greyfei.android.common.BaseActivity
import coder.greyfei.android.common.LogcatTextContainer
import coder.greyfei.android.ui.widget.LaunchActivityButton
import kotlin.random.Random

/**
 * Activity生命周期。
 * 类前缀缩写：AL
 *
 * Created by GreyFei on 2025/5/10
 */


class ALTestEntryActivity: BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            LaunchActivityButton(activityClz = ALActivityA::class)
            LaunchActivityButton(activityClz = ALActivityC::class)
            LaunchActivityButton(activityClz = ALActivityD::class)
        }
    }

}


class ALActivityA : BaseActivity() {

    companion object {
        private const val TAG = "ALActivityA"
    }

    private val mLogcatTextContainer = LogcatTextContainer(TAG)
    val showLog: MutableState<Boolean> = mutableStateOf(true)

    override fun onStart() {
        super.onStart()
        mLogcatTextContainer.startLogcat()
    }

    override fun onStop() {
        super.onStop()
        mLogcatTextContainer.stopLogcat()
    }

    @Composable
    override fun ComposeContentView() {
        Column {
            Text(text = "没有配置android:configChanges")

            Button(onClick = {
                ALActivityB::class.launch()
            }) {
                Text(text = "跳转到ALActivityB")
            }

            Button(onClick = { finish() }) {
                Text(text = "调用finish()")
            }

            Button(onClick = {
                Log.d(TAG, "打印日志${Random.nextInt()}")
            }) {
                Text(text = "打印日志")
            }

            Button(onClick = {
                showLog.value = !showLog.value
            }) {
                Text(text = "显示/隐藏LogcatText")
            }

            mLogcatTextContainer.LogcatText(showLog = showLog)
        }
    }

}

class ALActivityB : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            Button(onClick = { finish() }) {
                Text(text = "调用finish()")
            }
        }
    }

}

class ALActivityC : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            Text(text = "配置android:configChanges=\"orientation\"")
        }
    }

}

class ALActivityD : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            Text(text = "配置android:configChanges=\"orientation|screenSize\"")
        }
    }

}