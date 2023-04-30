package com.example.app.ui.screens

/**
 * Class:MainFrame
 * @author: tangyu
 * Description:
 * @Date:  2022/10/19
 *

 */
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.model.entity.NavigationItem
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun MainFrame(onNavigateToArticle: () -> Unit = {}) {

    val navigationItems = listOf(

        NavigationItem(title = "学习", icon = Icons.Filled.Home),
        NavigationItem(title = "任务", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person)

    )

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(modifier = Modifier.navigationBarsWithImePadding(),
        bottomBar = {
        Column() {
            BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(selected = currentNavigationIndex == index,
                        selectedContentColor = Color(0xff149ee7),
                        unselectedContentColor = Color(0xff999999),
                        onClick = {
                            currentNavigationIndex = index
                        },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = "")

                        },
                        label = {
                            Text(navigationItem.title)
                        }
                    )

                }
            }
        }

    }) {
        Box(modifier = Modifier.padding(it)) {
            when (currentNavigationIndex) {
                0 -> StudyScreen() {
                    onNavigateToArticle()
                }
                1 -> TaskScreen()
                2 -> MineScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}

