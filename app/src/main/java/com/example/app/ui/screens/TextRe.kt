package com.example.app.ui.screens

/**
 * Class:TextRe
 * @author: tangyu
 * Description:
 * @Date:  2023-07-06
 *

 */
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import java.util.Random

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Test1() {

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }
    Log.d("tedu","重组1"+currentNavigationIndex)

    LaunchedEffect(Unit){
        for (i in 0..10000){
            delay(2000)
            currentNavigationIndex=Random().nextInt(10)
        }

    }
    Column {
        Text("$currentNavigationIndex", modifier = Modifier.clickable {
            currentNavigationIndex=Random().nextInt(10)
        })
        Column {
            Text("sldfjksldfjkl$currentNavigationIndex")
            if(currentNavigationIndex%2==1)
                Test2()
        }
    }

}


@Preview
@Composable
fun preTest1(){
    Test1()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Test2(b1:PagerState=rememberPagerState()) {
    var test2 by rememberSaveable {
        mutableStateOf("test2")
    }

    Log.d("tedu","重组2"+test2.hashCode())
    Log.d("tedu","重组2"+b1.hashCode())

    Text(test2,modifier=Modifier.clickable {
        test2="test2 change"
    }
    )
     Test3()


//    Text(dsfsdf,modifier=Modifier.clickable {
//        change()
//    })

}
@Composable
fun Test3() {
    var test3 by rememberSaveable {
        mutableStateOf("test3")
    }
    Log.d("tedu","重组3"+test3.hashCode())

    Text(test3,modifier=Modifier.clickable {
        test3="test3 change"
    }
    )

//    Text(dsfsdf,modifier=Modifier.clickable {
//        change()
//    })

}
