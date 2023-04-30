package com.example.app.ui.screens

/**
 * Class:ArticleDetailScreen
 * @author: tangyu
 * Description:
 * @Date:  2023/1/3
 *

 */
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DisabledByDefault
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.components.WebView
import com.example.app.ui.components.rememberWebViewState
import com.google.accompanist.insets.statusBarsPadding


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleDetailScreen(onBack: () -> Unit) {
    var fontSize by remember{
        mutableStateOf(1.0f)
    }
    BottomSheetScaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "文章详情",
                    modifier = Modifier.wrapContentWidth(),
                    textAlign = TextAlign.Center,


                    fontSize = 18.sp
                )
            }, navigationIcon = {

                Icon(
                    imageVector = Icons.Default.NavigateBefore,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onBack()
                    })
            },
                actions = {
                    Icon(
                        imageVector = Icons.Default.DisabledByDefault,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onBack()
                        })
                }
            )

        },
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .statusBarsPadding(),
        sheetContent = {
            Column() {
                Text("字体大小")

//                Slider(value = , onValueChange = )
            }

        },

        ) {
        WebView(rememberWebViewState(data = "", baseUrl = "https://www.baidu.com"))
    }
}

@Preview
@Composable
fun ArticleDetailScreenPreview() {
    ArticleDetailScreen({})
}

