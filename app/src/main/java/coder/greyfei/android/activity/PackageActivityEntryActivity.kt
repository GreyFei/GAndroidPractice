package coder.greyfei.android.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import coder.greyfei.android.common.BaseActivity
import coder.greyfei.android.ui.widget.LaunchActivityButton

/**
 * Created by GreyFei on 2025/5/10
 */
class PackageActivityEntryActivity : BaseActivity() {

    override val needLogLifecycle: Boolean = false

    @Composable
    override fun ComposeContentView() {
        Column {
            LaunchActivityButton(ALTestEntryActivity::class, "Activity Lifecycle Test")
            LaunchActivityButton(ALMTestEntryActivity::class, "Activity Launch Mode Test")
        }
    }

}