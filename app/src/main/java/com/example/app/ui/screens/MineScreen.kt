package com.example.app.ui.screens

/**
 * Class:StudyScreen
 * @author: tangyu
 * Description:
 * @Date:  2022/10/26
 *

 */
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.components.TopAppBar

@Composable
fun MineScreen() {
    var s by remember{
        mutableStateOf(5)
    }
    Text(text = "${s}沃日", modifier = Modifier.height(40.dp).clickable {
        s = 10
    })

}

@Preview
@Composable
fun MineScreenPreview() {
    MineScreen()
}

/**
 */
@Composable
fun test() {




}

