package coder.greyfei.android

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coder.greyfei.android.activity.PackageActivityEntryActivity
import coder.greyfei.android.common.BaseActivity
import coder.greyfei.android.ui.theme.GAndroidPracticeTheme
import coder.greyfei.android.ui.widget.LaunchActivityButton

class MainActivity : BaseActivity() {

    override val needLogLifecycle: Boolean get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    override fun ComposeContentView() {
        Column {
            LaunchActivityButton(activityClz = PackageActivityEntryActivity::class)
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GAndroidPracticeTheme {
        Greeting("Android")
    }
}