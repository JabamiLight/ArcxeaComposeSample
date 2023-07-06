 package demo

/**
 * Class:DeriveStateOf
 * @author: tangyu
 * Description:
 * @Date:  2023-04-30
 *

 */
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DeriveStateOf() {
    //处理remember name 的问题，某些不会重组的情况
    val highPriorityKeywords: List<String> = listOf("Review", "Unblock", "Compose")
    var other by remember {
        mutableStateOf("other1")
    }
    var other2 by remember {
        mutableStateOf("other2")
    }
    val todoTasks = remember { mutableStateListOf<String>() }
    val highPriorityTasks by remember(highPriorityKeywords) {
        derivedStateOf { todoTasks.filter { it.contains("Review") } }
    }

    Column() {
        Text(text=other)
        Text(text=other2,Modifier.clickable {
            other="bianhua1"
            other2="bianhua2"
        })

        Text(text = "chongzu todu", modifier = Modifier.clickable {
            todoTasks.add("Review")
        })
        Log.d("tedu","重组")
        todoTasks.forEach {
            Text(text = it)
        }
        highPriorityTasks.forEach {
            Text(text = it)
        }

    }





}

@Preview
@Composable
fun DeriveStateOfPreview() {
    DeriveStateOf()
}

