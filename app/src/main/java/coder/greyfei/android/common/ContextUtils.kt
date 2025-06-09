package coder.greyfei.android.common

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

/**
 * Created by GreyFei on 2025/5/10
 */


fun Context.launch(activityClz: KClass<out ComponentActivity>) {
    val intent = Intent(this, activityClz.java)
    this.startActivity(intent)
}