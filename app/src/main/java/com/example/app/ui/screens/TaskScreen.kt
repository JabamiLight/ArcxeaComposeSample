package com.example.app.ui.screens

/**
 * Class:StudyScreen
 * @author: tangyu
 * Description:
 * @Date:  2022/10/26
 *

 */
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.components.ChartView
import com.example.app.ui.components.CircleRing
import com.example.app.ui.components.TopAppBar
import com.example.app.viewmodel.TaskViewModel

@Composable
fun TaskScreen(taskViewModel: TaskViewModel = viewModel()) {


    var boxWith: Int
    with(LocalConfiguration.current) {
        boxWith = screenWidthDp
    }
    taskViewModel.updatePointPercent()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(Color(0xff149ee7), Color.White)))
    ) {

        TopAppBar() {
            Text(text = "学习任务", color = Color.White)
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = taskViewModel.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(top = 8.dp)) {

                    CircleRing(size = boxWith / 2, taskViewModel)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            buildAnnotatedString {
                                append(taskViewModel.pointOfYear.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("分")
                                }

                            },
                            fontSize = 36.sp,
                            color = Color.White,

                            )
                        Text(text = "学年积分", fontSize = 12.sp, color = Color.White)
                    }

                }
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-30).dp)
                ) {
                    Column {
                        Text(
                            text = "${taskViewModel.totalPointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(text = "学年规定积分", fontSize = 12.sp, color = Color.White)

                    }
                    Column {
                        Text(
                            text = "${taskViewModel.totalPointOfYear - taskViewModel.pointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(text = "学年规定积分", fontSize = 12.sp, color = Color.White)

                    }


                }
            }

            //学习明细
            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                        .padding(8.dp)
                ) {

                    Text(text = "学习明细", fontSize = 16.sp, color = Color(0xff333333))

                    Text(text = "最近一周积分情况", fontSize = 16.sp, color = Color(0xff999999))

                    ChartView(
                        points = taskViewModel.pointsOfWeek,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    //日期
                    Row() {
                        taskViewModel.weeks.forEach {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                color = Color(0xFF999999),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            item {

                val inlineContentId = "inlineContentId"

                val secondaryAnnotatedText = buildAnnotatedString {
                    append("secondaryText")
                    appendInlineContent(inlineContentId, "[icon]")
                }
                val inlineContent = mapOf(
                    Pair(inlineContentId,
                        InlineTextContent(
                            Placeholder(
                                width = 14.sp,
                                height = 14.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.HelpOutline,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    Log.i("===", "点击了问号")
                                })
                        })
                )
                Text(secondaryAnnotatedText, inlineContent = inlineContent, textAlign = TextAlign.Center)

                Column {
                    OutlinedButton(
                        onClick = {},
                        shape= RoundedCornerShape(32.dp),
                        border = BorderStroke(1.dp, Color(0xff149ee7)),
                        colors = androidx.compose.material.ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.Red,
                            contentColor = Color(0xffffffff)
                        ),
                    ) {

                        Text(text = "查看更多", fontSize = 16.sp)

                    }

                    LinearProgressIndicator(progress = 0.2f)


                }




            }

        }


    }
}


@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}
