package com.example.app.ui.screens

/**
 * Class:StudyScreen
 * @author: tangyu
 * Description:
 * @Date:  2022/10/26
 *

 */
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.components.*
import com.example.app.ui.components.TopAppBar
import com.example.app.viewmodel.ArticleViewModel
import com.example.app.viewmodel.MainViewModel
import com.example.app.viewmodel.VideoViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StudyScreen(
    vm: MainViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    videoViewModel: VideoViewModel = viewModel(),
    onNavigateToArticle: () -> Unit = {},
) {
    Column {
        TopAppBar(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary)
                    )
                )
                .padding(horizontal = 8.dp)

        ) {

            Row(
                Modifier
                    .background(
                        color = Color(0x33ffffff), shape = RoundedCornerShape(15.dp)
                    )
                    .padding(5.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "", tint = Color.White)
                Text(text = "搜索感兴趣的内容", color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "学习\n进度", fontSize = 10.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "26%", fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "",
                tint = Color.White
            )
        }

        TabRow(
            selectedTabIndex = vm.categoryIndex,
            backgroundColor = Color(0x22149ee7),
            contentColor = Color(0xff149ee7),
            divider = {
                TabRowDefaults.Divider(color = Color(0xff000000))
            },
            indicator = {
                val currentTabPosition = it[vm.categoryIndex]
                //修改指示器长度
                val currentTabWidth by animateDpAsState(
                    targetValue = currentTabPosition.width / 4,
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                )
                //修改指示器偏移量为居中
                val indicatorOffset by animateDpAsState(
                    targetValue = currentTabPosition.left + (currentTabPosition.width / 2 - currentTabWidth / 2),
                    animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
                )
                //自带的Indicator指示器，只需改Modifier就可以了
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.BottomStart)
                        .offset(x = indicatorOffset)
                        .width(currentTabWidth)
                        .height(2.dp)//修改指示器高度为1dp，默认2dp
                )
            }
        ) {
            vm.category.forEachIndexed { index, categor ->
                Tab(
                    selected = index == vm.categoryIndex, onClick = {
                        vm.categoryIndex = index
                    },
                    selectedContentColor = Color(0xff149ee7),
                    unselectedContentColor = Color(0xff149ee7)

                ) {
                    Text(
                        text = categor.title,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

            }


        }


        TabRow(
            selectedTabIndex = vm.typeIndex,
            backgroundColor = Color.Transparent,
            contentColor = Color(0xff149ee7),
            indicator = {

            },
            divider = {

            }
        ) {
            vm.types.forEachIndexed { index, bean ->
                LeadingIconTab(
                    selected = index == vm.typeIndex, onClick = {
                        vm.typeIndex = index
                    },
                    selectedContentColor = Color(0xff149ee7),
                    unselectedContentColor = Color(0xff666666),
                    text = {
                        Text(
                            text = bean.title,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    },
                    icon = {
                        Icon(imageVector = bean.icon, contentDescription = "")
                    }

                )
            }
        }
        //轮播图

        LazyColumn() {

            item {
                SwiperContent(vm = vm)
            }
            item {
                NotificationContent(vm = vm)
            }
            if (vm.typeIndex == 0) {
                items(articleViewModel.list) {
                    ArticleItem(
                        article = it,
                        articleViewModel.list.last() === it,
                        modifier = Modifier.clickable {

                            onNavigateToArticle.invoke()

                        })
                }
            } else {
                items(videoViewModel.list) {
                    VideoItem(it, videoViewModel.list.last() === it)
                }
            }
        }


    }
}

@Preview
@Composable
fun StudyScreenPreview() {
    StudyScreen()
}

