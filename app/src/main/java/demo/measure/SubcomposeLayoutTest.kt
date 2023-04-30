package demo.measure

/**
 * Class:SubcomposeLayoutTest
 * @author: tangyu
 * Description:
 * @Date:  2022/12/5
 *

 */
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SubcomposeRow(
    modifier: Modifier,
    text: @Composable () -> Unit,
    divider: @Composable (Int) -> Unit // 传入高度
){
    SubcomposeLayout(modifier = modifier) { constraints->
        var maxHeight = 0
        var placeables = subcompose("text", text).map {
            var placeable = it.measure(constraints)
            maxHeight = placeable.height.coerceAtLeast(maxHeight)
            placeable
        }
        var dividerPlaceable = subcompose("divider") {
            divider(maxHeight)
        }.map {
            it.measure(constraints.copy(minWidth = 0))
        }
        assert(dividerPlaceable.size == 1, { "DividerScope Error!" })

        var midPos = constraints.maxWidth / 2
        layout(constraints.maxWidth, constraints.maxHeight){
            placeables.forEach {
                it.placeRelative(0, 0)
            }
            dividerPlaceable.forEach {
                it.placeRelative(midPos, 0)
            }
        }
    }
}

@Preview
@Composable
fun SubcomposeLayoutTestPreview() {
    SubcomposeRow(
        modifier = Modifier
            .fillMaxWidth(),
        text = {
            Text(text = "Left", Modifier.wrapContentWidth(Alignment.Start))
            Text(text = "Right", Modifier.wrapContentWidth(Alignment.End))
        }
    ) {
        var heightPx = with( LocalDensity.current) { it.toDp() }
        Divider(
            color = Color.Black,
            modifier = Modifier
                .width(4.dp)
                .height(heightPx)
        )
    }
}
