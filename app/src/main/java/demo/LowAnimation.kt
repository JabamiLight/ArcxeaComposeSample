package demo

/**
 * Class:LowAnimation
 * @author: tangyu
 * Description:
 * @Date:  2022/11/13
 *

 */
import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.VectorConverter
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
@Preview
fun AnimatableTest() {

    val color = remember {
        Animatable(Color.Gray)
    }
    var ok by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = ok, block = {
        color.animateTo(if (ok) Color.Green else Color.Red)
    })

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color.value)
        .clickable {
            ok = ok.not()
        })
}

@Composable
@Preview
fun TargetBasedAnimationTest() {

//    val converter = (Color.VectorConverter)(Color.Red.colorSpace)
//    val anim = remember {
//        TargetBasedAnimation(
//            animationSpec = tween(200),
//            typeConverter = converter,
//            initialValue = Color.Red,
//            targetValue = Color.Blue,
//        )
//    }


    var state by remember {
        mutableStateOf(0)
    }
    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(2000),
            typeConverter = Float.VectorConverter,
            initialValue = 100f,
            targetValue = 300f
        )
    }
    var playTime by remember { mutableStateOf(0L) }
    var animationValue by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(state) {
        val startTime = withFrameNanos { it }
        println("进入协程：")
        do {
            playTime = withFrameNanos { it } - startTime
            animationValue = anim.getValueFromNanos(playTime).toInt()
        } while (!anim.isFinishedFromNanos(playTime))

    }
    Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .size(animationValue.dp)
            .background(Color.Red, shape = RoundedCornerShape(animationValue / 5))
            .clickable {
                state++
            }, contentAlignment = Alignment.Center
        ) {
            Text(
                text = animationValue.toString(),
                style = TextStyle(color = Color.White, fontSize = (animationValue / 5).sp)
            )
        }
    }
}


data class MySize(val width: Dp, val height: Dp)

@Composable
fun MyAnimation(targetSize: MySize) {
    val animSize: MySize by animateValueAsState<MySize, AnimationVector2D>(
        targetSize,
        TwoWayConverter(
            convertToVector = { size: MySize ->
                // Extract a float value from each of the `Dp` fields.
                AnimationVector2D(size.width.value, size.height.value)
            },
            convertFromVector = { vector: AnimationVector2D ->
                MySize(vector.v1.dp, vector.v2.dp)
            }
        )
    )
}

fun Modifier.swipeToDismiss(
    onDismissed: () -> Unit
): Modifier = composed {
    val offsetX = remember { Animatable(0f) }

    pointerInput(Unit) {
        // Used to calculate fling decay.
        val decay = splineBasedDecay<Float>(this)
        // Use suspend functions for touch events and the Animatable.
        coroutineScope {

            while (true) {
                // Detect a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                val velocityTracker = VelocityTracker()
                // Stop any ongoing animation.
                offsetX.stop()
                awaitPointerEventScope {
                    //里头是一个死循环，内部抬起会跳出

                    horizontalDrag(pointerId) { change ->
                        // Update the animation value with touch events.
                        launch {
                            Log.d("tedu", "处理拖动")
                            offsetX.snapTo(
                                offsetX.value + change.positionChange().x
                            )
                        }
                        Log.d("tedu", "计算速度值")
                        velocityTracker.addPosition(
                            change.uptimeMillis,
                            change.position
                        )
                        Log.d("tedu", "中间")
                        // No longer receiving touch events. Prepare the animation.
                        val velocity = velocityTracker.calculateVelocity().x
                        val targetOffsetX = decay.calculateTargetValue(
                            offsetX.value,
                            velocity
                        )
                        // The animation stops when it reaches the bounds.
                        offsetX.updateBounds(
                            lowerBound = -size.width.toFloat(),
                            upperBound = size.width.toFloat()
                        )
                        launch {
                            Log.d("tedu", "处理回弹或者退出")
                            if (targetOffsetX.absoluteValue <= size.width) {
                                // Not enough velocity; Slide back.
                                offsetX.animateTo(
                                    targetValue = 0f,
                                    initialVelocity = velocity
                                )
                            } else {
                                // The element was swiped away.
                                offsetX.animateDecay(velocity, decay)
                                onDismissed()
                            }
                        }
                    }
                }
            }
        }
    }
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}