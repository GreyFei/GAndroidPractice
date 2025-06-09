package coder.greyfei.android.ui.widget

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coder.greyfei.android.common.launch
import kotlin.reflect.KClass

/**
 * Created by GreyFei on 2025/6/7
 */


@Composable
fun Context.LaunchActivityButton(
    activityClz: KClass<out ComponentActivity>,
    customBtnText: String? = null,
) {
    Button(
        modifier = Modifier.padding(horizontal = 8.dp),
        onClick = {
            this.launch(activityClz)
        }
    ) {
        val btnText = customBtnText.takeIf { !it.isNullOrEmpty() }
            ?: "启动${activityClz.simpleName}"
        Text(text = btnText)
    }
}
