package com.example.app.ui.components

/**
 * Class:SwiperContent
 * @author: tangyu
 * Description:
 * @Date:  2022/11/27
 *

 */
import android.util.Log
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.sql.Time
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(vm: MainViewModel) {

    val virtualCount = 100000

    val initial = virtualCount / 2
    val pagerState = rememberPagerState(initial)

    //手动取消,自己控制
//    val scope = rememberCoroutineScope()
//    DisposableEffect(Unit) {
//        val timer = Timer()
//
//        this.onDispose { }
//    }


    //dispose时候取消
    LaunchedEffect(Unit) {
        while (true){
            delay(3000)
            pagerState.animateScrollToPage(pagerState.currentPage+1)
        }

    }
    HorizontalPager(
        count = virtualCount,
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(
                RoundedCornerShape(8.dp)
            )
    ) { index ->

        val actualIndex = index % 5
        AsyncImage(
            model = vm.swiperData[actualIndex].imageUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 3f), contentScale = ContentScale.Crop
        )
    }
}




