package demo

/**
 * Class:MyRow
 * @author: tangyu
 * Description:
 * @Date:  2022/11/1
 *

 */
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import kotlin.math.max


/**
 * 自定义row测试
 */
@Composable
fun MyRow(modifier: Modifier=Modifier, content: @Composable () -> Unit) {

    //1、布局进行测量
    val measurePolicy = MeasurePolicy { measurables, constraints ->
        //1、开始测量子视图
        val placeables = measurables.map { child ->
            //设置宽高
            child.measure(Constraints(0, constraints.maxWidth, 0, constraints.maxHeight))

        }
        //测量后简单计算高度

        var rowWidth = constraints.minWidth
        var rowHeight = constraints.minHeight
        placeables.forEach {
            rowHeight = max(rowHeight, it.height)
            rowWidth+=it.width
            
        }

        var xPosition = 0
        //2、防止childern layout方法参数为父布局宽高
        layout(rowWidth,rowHeight) {
            placeables.forEach {placeable ->
                placeable.placeRelative(xPosition,0)
                xPosition += placeable.width
            }
        }
    }

    Layout(content = content, modifier = modifier, measurePolicy =measurePolicy)


}
@Preview
@Composable
fun MyRowPreview() {
    MyRow() {
        Text(text = "111")
        Text(text = "222")
    }
    
}


//函数式接口转换，kotlin的接口前面加一个fun才能在调用的时候直接转为接口
//不加fun就需要新建MyListener对象
//平时定义的尾部调用的方法，参数是一个function而不是一个接口
fun interface MyListener { // fun interface 我的理解就是函数式接口,这里必须包含一个抽象方法(也就是没有函数体),没有抽象方法编译不通过
    fun onCheckdChange(param1: String, param2: String)
}
fun setMyListener(listener: MyListener) {
    listener.onCheckdChange("name", "zyt")
}