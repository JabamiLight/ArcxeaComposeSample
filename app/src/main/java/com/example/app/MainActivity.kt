package com.example.app

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.app.ui.components.NavHostApp
import com.example.app.ui.screens.MainFrame
import com.example.app.ui.screens.Test1
import com.example.app.ui.theme.APPTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取状态栏高度
//        var statusBarHeight = 0
//        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
//        if (resourceId > 0) {
//            statusBarHeight = resources.getDimensionPixelSize(resourceId)
//        }

        //处理不同机型，状态栏不透明问题
//        window.statusBarColor = Color.Transparent.value.toInt()
//        //处理不同机型，导航栏遮盖内容问题
//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        //让内容，显示在状态栏和系统导航栏后面：状态栏和导航栏会遮盖部分内容
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            APPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //状态栏预设
                    ProvideWindowInsets {
//                        NavHostApp()
                        Test1()
                    }
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
