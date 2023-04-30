package com.example.app.ui.components

/**
 * Class:TopAppBar
 * @author: tangyu
 * Description:
 * @Date:  2022/10/27
 *

 */
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.APPTheme
import com.example.app.ui.theme.AppBarHeight
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TopAppBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    ProvideWindowInsets {

        val systemUiController = rememberSystemUiController()

        LaunchedEffect(key1 = Unit) {
            systemUiController.setStatusBarColor(Color.Transparent)
        }

        val density = LocalDensity.current
        val statsBar = LocalWindowInsets.current.statusBars
        var statusBarHeight = 25.dp
        if (statsBar.top > 0) {
            statusBarHeight = with(density) {
                statsBar.top.toDp()
            }
        }
        Row(
            modifier = Modifier
                .then(modifier)
                .fillMaxWidth()
                .height(AppBarHeight + statusBarHeight)
                .padding(top = statusBarHeight),

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically


        ) {

            content()
        }
    }


}

@Preview
@Composable
fun TopAppBarPreview() {
    APPTheme() {
        TopAppBar {
            Text(text = "标题")
        }

    }
}

