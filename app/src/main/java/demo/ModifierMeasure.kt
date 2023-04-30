package demo

/**
 * Class:ModifierMeasure
 * @author: tangyu
 * Description:
 * @Date:  2022/11/2
 *

 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
enum class CornerPosition {
    TOPLEFT,
    TOPRIGHT,
    BOTTOMLEFT,
    BOTTOMRIGHT
}

fun Modifier.customCornerPosLayout(pos:CornerPosition)=layout{measurable, constraints ->
        val placeable=measurable.measure(constraints)
        layout(constraints.maxWidth,constraints.maxHeight){
            when(pos){
                CornerPosition.TOPLEFT->placeable.place(0,0)
                CornerPosition.TOPRIGHT->placeable.place(constraints.maxWidth-placeable.width,0)
                CornerPosition.BOTTOMLEFT->placeable.place(0,constraints.maxHeight-placeable.height)
                CornerPosition.BOTTOMRIGHT->placeable.place(constraints.maxWidth-placeable.width,constraints.maxHeight-placeable.height)
            }
        }

}


@Composable
fun ModifierMeasure() {

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
    ) {
        Box(modifier =Modifier
            .customCornerPosLayout(CornerPosition.TOPLEFT)
            .size(10.dp)
            .background(Color.Blue, CircleShape)

        )
    }
}

@Preview
@Composable
fun ModifierMeasurePreview() {
    ModifierMeasure()
}

