package demo

/**
 * Class:GestureDemo
 * @author: tangyu
 * Description:
 * @Date:  2022/11/2
 *

 */
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun GestureDemo() {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        clickableDemo()
        doubluClickDemo()
        touPositionDemo()
        horizontalDragDemo()
        verticalDragDemo()
        dragDemo()
    }
}

@Composable
fun clickableDemo() {
    Spacer(modifier = Modifier.height(10.dp))
    Text("单击事件")
    var clickCount by remember {
        mutableStateOf(0)
    }
    Button(onClick = { clickCount++ }) {
        Text("Clicked $clickCount")
    }

}

@Composable
fun doubluClickDemo() {
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "单机 双击 长按，按下事件监听")

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(MaterialTheme.colors.primary)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        Toast
                            .makeText(context, "按下", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onDoubleTap = {
                        Toast
                            .makeText(context, "双击", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onLongPress = {
                        Toast
                            .makeText(context, "长按", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onTap = {
                        Toast
                            .makeText(context, "单机", Toast.LENGTH_SHORT)
                            .show()
                    }


                )

                detectDragGestures { change, dragAmount ->


                }
            }
    ) {
        Text(text = "Click me", modifier = Modifier.background(Color.Red))
    }


}

@Composable
fun touPositionDemo() {
    var touchedX by remember {
        mutableStateOf(0f)
    }
    var touchedY by remember {
        mutableStateOf(0f)
    }
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(Color.Cyan)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()

                    touchedX = change.position.x
                    touchedY = change.position.y

                }
            }, contentAlignment = Alignment.Center
    ) {
        Column() {
            Text(text = "这是一个监听触摸位置组件")
            Text(text = "touchedX=${touchedX.toInt()} touchedY=${touchedY.toInt()}")
        }
    }
}


@Composable
fun verticalDragDemo() {
    Text("垂直拖动")
    var offsetY by remember {
        mutableStateOf(0f)
    }
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(500.dp),
        contentAlignment = Alignment.Center

    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset {
                    IntOffset(0, offsetY.roundToInt())
                }
                .background(MaterialTheme.colors.primary)
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { fl: Float ->
                        Log.d("tedu", "fl ${fl}")
                        offsetY += fl
                    }


                )

        )
    }
}

@Composable
fun horizontalDragDemo() {
    Text("水平拖动")
    var offsetX by remember {
        mutableStateOf(0f)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center

    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset {
                    IntOffset(offsetX.roundToInt(), 0)
                }
                .background(MaterialTheme.colors.primary)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { fl: Float ->
                        offsetX += fl
                    }

                )

        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun dragDemo() {
    Text(text = "任意拖动")
    var offsetX by remember {
        mutableStateOf(0f)
    }
    var offsetY by remember {
        mutableStateOf(0f)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .size(500.dp), contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.Surface(
            color = Color.Red,
            modifier = Modifier
                .size(100.dp)
                .offset {
                    IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
                }
                .pointerInput(Unit) {

                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
                .onPlaced {
//                    Log.d("tedu","${it.localToRoot(Offset(0f,0f))}")
//                    Log.v("tedu","${it.parentCoordinates?.localToRoot(Offset(0f,0f))}")
//                    Log.i("tedu","${it.localPositionOf(it.parentCoordinates!!,Offset(0f,0f))}")
                    Log.e("tedu", "${it.positionInParent()}")
                }
                .onGloballyPositioned {

                }
        ) {

        }

    }
}


@Preview
@Composable
fun GestureDemoPreview() {
    GestureDemo()
}


@Preview
@Composable
fun swipeToDismissTest() {

    Text(text = "滑动删除", modifier = Modifier
        .fillMaxWidth()

        .height(50.dp)
        .background(Color.Green).swipeToDismiss {

        },
        textAlign = TextAlign.Center

    )
}
@Preview
@Composable
fun NestScroll(){
    val gradient = Brush.verticalGradient(0f to Color.Gray, 1000f to Color.White)
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        Column {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Scroll here",
                        modifier = Modifier
                            .border(12.dp, Color.DarkGray)
                            .background(brush = gradient)
                            .padding(24.dp)
                            .height(150.dp)
                    )
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}