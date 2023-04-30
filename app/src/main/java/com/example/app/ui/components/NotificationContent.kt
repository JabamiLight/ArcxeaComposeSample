package com.example.app.ui.components

/**
 * Class:NotificationContent
 * @author: tangyu
 * Description:
 * @Date:  2022/11/28
 *

 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.APPTheme
import com.example.app.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NotificationContent(vm: MainViewModel) {

    val virtualCount = 666666

    val initial = virtualCount / 2
    val pagerState = rememberPagerState(initial)


    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0x22149EE7), RoundedCornerShape(8.dp))
            .height(45.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text("最新活动", color = Color(0xFF149EE7), fontSize = 14.sp)

        Spacer(modifier = Modifier.width(8.dp))

        VerticalPager(
            count = virtualCount,
            state = pagerState,
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start

        ) { index ->

            val actualIndex = index % vm.notifications.size
            Text(
                text = vm.notifications[actualIndex],
                color = Color(0xFF333333),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "更多",
            color = Color(0xFF666666),
            fontSize = 14.sp,
            maxLines = 1,
        )
    }

}


