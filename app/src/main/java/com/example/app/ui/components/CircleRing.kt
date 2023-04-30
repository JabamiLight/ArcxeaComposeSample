package com.example.app.ui.components

/**
 * Class:CircleRing
 * @author: tangyu
 * Description:
 * @Date:  2022/12/1
 *

 */
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.viewmodel.TaskViewModel

@Composable
fun CircleRing(size: Int,vm:TaskViewModel) {
    Canvas(modifier = Modifier.size(size.dp)) {
        drawArc(
            Color(0,0,0,15),
            startAngle = 160f,
            sweepAngle = 220f,
            useCenter = false,
            style = Stroke(30f, cap = StrokeCap.Round)
        )
        drawArc(
            Color.White,
            startAngle = 160f,
            sweepAngle = vm.pointOfYearPercent,
            useCenter = false,
            style = Stroke(30f, cap = StrokeCap.Round)
        )
    }

}

@Preview
@Composable
fun CircleRingPreview() {
}

