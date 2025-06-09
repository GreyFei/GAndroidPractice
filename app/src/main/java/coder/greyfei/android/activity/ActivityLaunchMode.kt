package coder.greyfei.android.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coder.greyfei.android.common.BaseActivity
import coder.greyfei.android.ui.widget.LaunchActivityButton

/**
 * Activity启动模式。
 * 类前缀缩写：ALM
 *
 * Created by GreyFei on 2025/6/6
 */


class ALMTestEntryActivity : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            LaunchActivityButton(activityClz = ALMSingleInstanceTestEntryActivity::class)
        }
    }

}

// region ======== SingleInstance ========
class ALMSingleInstanceTestEntryActivity : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            val intro = """
                SingleInstance启动模式测试。
                各个Activity启动模式如下：
                Test1 = Standard
                Test2 = SingleInstance
                Test3 = Standard
                启动顺序：1 -> 2 -> 3 -> 1 -> 2 -> 3
            """.trimIndent()
            Text(text = intro)
            LaunchActivityButton(
                activityClz = ALMSingleInstanceTestActivity1::class,
                "SingleInstance启动模式",
            )
        }
    }

}

class ALMSingleInstanceTestActivity1 : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            LaunchActivityButton(activityClz = ALMSingleInstanceTestActivity2::class)
        }
    }

}

class ALMSingleInstanceTestActivity2 : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            LaunchActivityButton(activityClz = ALMSingleInstanceTestActivity3::class)
        }
    }

}

class ALMSingleInstanceTestActivity3 : BaseActivity() {

    @Composable
    override fun ComposeContentView() {
        Column {
            LaunchActivityButton(activityClz = ALMSingleInstanceTestActivity1::class)
        }
    }

}
// endregion