package coder.greyfei.android.common

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coder.greyfei.android.ui.theme.GAndroidPracticeTheme
import kotlin.reflect.KClass

/**
 * Created by GreyFei on 2025/5/10
 */
abstract class BaseActivity : ComponentActivity() {

    companion object {
        private const val TAG = "BaseActivity"
    }

    open val needLogLifecycle = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle { "onCreate" }
        setupComposeContentView()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    protected open fun setupComposeContentView() {
        enableEdgeToEdge()
        setContent {
            GAndroidPracticeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = this::class.simpleName.orEmpty() ) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                        )
                    },
                ) { innerPadding ->
                    Surface(Modifier.padding(innerPadding)) {
                        ComposeContentView()
                    }
                }
            }
        }
    }

    @Composable
    abstract fun ComposeContentView()

    override fun onRestart() {
        super.onRestart()
        logLifecycle { "onRestart" }
    }

    override fun onStart() {
        super.onStart()
        logLifecycle { "onStart" }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logLifecycle { "onRestoreInstanceState\nsavedInstanceState=$savedInstanceState" }
    }

    override fun onResume() {
        super.onResume()
        logLifecycle { "onResume" }
    }

    override fun onPause() {
        super.onPause()
        logLifecycle { "onPause" }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logLifecycle { "onSaveInstanceState\noutState=$outState" }
    }

    override fun onStop() {
        super.onStop()
        logLifecycle { "onStop" }
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle { "onDestroy" }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        logLifecycle { "onNewIntent\nintent=$intent" }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        logLifecycle { "onConfigurationChanged" }
    }

    protected fun KClass<out ComponentActivity>.launch() {
        this@BaseActivity.launch(this)
    }

    private inline fun logLifecycle(msg: () -> String) {
        if (needLogLifecycle) {
            Log.d(TAG, "logLifecycle [${this::class.simpleName}]: ${msg()}")
        }
    }

}