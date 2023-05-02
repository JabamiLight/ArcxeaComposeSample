package demo.state

/**
 * Class:DeriveStateOf
 * @author: tangyu
 * Description:
 * @Date:  2023-05-02
 *

 */
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class TextStateBean{
    val count=mutableStateOf<Int>(0)
    var count2=mutableStateOf<Int>(0)
}

@Composable
fun DeriveStateOf() {
    //測試1
//    TodoList()
    //測試2
    DeriveTest()

}



@Preview
@Composable
fun DeriveStateOfPreview() {
    DeriveStateOf()
}

@Composable
fun DeriveTest(){
    val state=TextStateBean()
    Log.d("tedu","重组")
//    val derive by remember {
//        derivedStateOf {
//            state.count2.value
//        }
//    }
    //这里未触发重组，但是也会拿到变化的值,launch
    LaunchedEffect(Unit){
        Log.d("tedu","launch")
        snapshotFlow {
            state.count2.value
        }.collect{
            Log.d("tedu","count ${state.count2.value}")
        }
    }
   subText(0, state)


}
@Composable
fun subText(derive: Int, state:TextStateBean) {
    Text("derive${derive}",Modifier.clickable {
        //更新的情況下會變化，触发重组
//        state=TextStateBean().also {
//            it.count2=5
//        }
        //直接使用不触发重组，不会变化
        //但是launchEffect可以接收变化
        //derive可以接收变化，如果有方法使用了derive就会触发重组
        state.count2.value=5
        //snapshotflow

    })
}

@Composable
fun TodoList(highPriorityKeywords: List<String> = listOf("Review", "Unblock", "Compose")) {

    val todoTasks = remember { mutableStateListOf<String>(
        "Review", "Unblock", "Compose"

    ) }

    // Calculate high priority tasks only when the todoTasks or highPriorityKeywords
    // change, not on every recomposition
    val highPriorityTasks by remember(highPriorityKeywords) {
        derivedStateOf { todoTasks.filter { highPriorityKeywords.contains(it) } }
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(highPriorityTasks) {
                Text(text=it,Modifier.clickable {
                    todoTasks.add("Review")
                })
            }
            items(todoTasks) {
                Text(it)
            }
        }
    }
}