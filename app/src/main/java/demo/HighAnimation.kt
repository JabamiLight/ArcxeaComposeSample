package demo

/**
 * Class:Animation
 * @author: tangyu
 * Description:
 * @Date:  2022/11/10
 *

 */
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.reflect.KProperty

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityTest() {
    Column() {

        var editable by remember { mutableStateOf(true) }

        Button(onClick = { editable = editable.not() }) {
            Text(text = "按钮1号")
        }
        AnimatedVisibility(visible = editable) {
            Text(
                text = "Edit",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(200.dp)
            )
        }
        //**=================动画组合============================*//
        var visible by remember { mutableStateOf(true) }
        Button(onClick = { visible = visible.not() }) {
            Text(text = "按钮2号")
        }
        val density = LocalDensity.current
        AnimatedVisibility(visible = visible,
            enter = slideInVertically {
                with(density) {
                    -40.dp.roundToPx()
                }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkHorizontally() + fadeOut()

        ) {
            Text(
                "Hello", modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .height(200.dp)
            )
        }


        //**====================理解执行，适合观察动画=========================*//
        // Create a MutableTransitionState<Boolean> for the AnimatedVisibility.
        val state = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }
        Column {
            AnimatedVisibility(visibleState = state) {
                Text(text = "Hello, world!")
            }

            // Use the MutableTransitionState to know the current animation state
            // of the AnimatedVisibility.
            Text(
                text = when {
                    state.isIdle && state.currentState -> "Visible"
                    !state.isIdle && state.currentState -> "Disappearing"
                    state.isIdle && !state.currentState -> "Invisible"
                    else -> "Appearing"
                }
            )
        }

        //**====================为子项添加进入和退出动画效果=========================*//

        var visible1 by remember { mutableStateOf(true) }
        Button(onClick = { visible1 = visible1.not() }) {
            Text(text = "按钮3号")
        }
        AnimatedVisibility(visible = visible1, enter = fadeIn(), exit = fadeOut()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .animateEnterExit(enter = slideInVertically(), exit = slideOutVertically())
                        .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                        .background(Color.Red)

                )
            }
        }


    }

}

@Preview
@Composable
fun AnimatedVisibilityTestPreview() {
    AnimatedVisibilityTest()
}

@Composable
@Preview
fun AniamteState() {
    var enable by remember {
        mutableStateOf(false)
    }

    val alpha by animateFloatAsState(animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing), targetValue = if (enable) 1f else 0.5f)
    Column() {
        Button(onClick = {enable=enable.not()}) {
            Text("Change")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer(alpha = alpha)
                .background(Color.Red)
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview
fun AnimatedContent() {
    Row {
        var count by remember { mutableStateOf(0) }
        Button(onClick = { count++ }) {
            Text("Add")
        }
        AnimatedContent(targetState = count, transitionSpec = {
            // Compare the incoming number with the previous number.
            if (targetState > initialState) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                slideInVertically { height -> height } + fadeIn() with slideOutVertically { height -> -height } + fadeOut()
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                slideInVertically { height -> -height } + fadeIn() with slideOutVertically { height -> height } + fadeOut()
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }) { targetCount ->
            Text(fontSize = 20.sp, text = "$targetCount", modifier = Modifier.background(Color.Red))
        }
    }
}

@Composable
@Preview
fun AnimateContentSize() {
    Column {
        var message by remember { mutableStateOf("Hello") }
        Button(onClick = { message = message.plus("hello") }) {
            Text("Change")
        }
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .animateContentSize()
        ) {
            Text(text = message)
        }
    }
}

@Composable
@Preview
fun Crossfade() {
    Column {
        var currentPage by remember { mutableStateOf("A") }
        Button(onClick = {
            if (currentPage == "A") {
                currentPage = "B"
            } else {
                currentPage = "A"
            }
        }) {
            Text("Change")
        }
        Crossfade(targetState = currentPage) { screen ->
            when (screen) {
                "A" -> Text("Page A", fontSize = 30.sp)
                "B" -> Text("Page B", fontSize = 30.sp)
            }
        }
    }
}

enum class BoxState {
    Collapsed, Expanded
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview
fun updateTransition() {
    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(currentState, label = "")
    val rect by transition.animateRect(label = "") {
        when (it) {
            BoxState.Collapsed -> Rect(0f, 0f, 100f, 100f)
            BoxState.Expanded -> Rect(100f, 100f, 300f, 300f)
        }
    }

    Box(modifier = Modifier
        .background(Color.Red)
        .offset(rect.left.dp, rect.top.dp)
        .size(rect.right.dp, rect.bottom.dp)
        .clickable {
            if (currentState == BoxState.Collapsed) currentState =
                BoxState.Expanded else currentState = BoxState.Collapsed
        })

}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
@Preview
fun updateTransitionCombine() {
    var selected by remember { mutableStateOf(false) }
// Animates changes when `selected` is changed.
    val transition = updateTransition(selected)
    val borderColor by transition.animateColor(label = "") { isSelected ->
        if (isSelected) Color.Magenta else Color.White
    }
    val elevation by transition.animateDp(label = "") { isSelected ->
        if (isSelected) 10.dp else 2.dp
    }
    Surface(
        onClick = { selected = !selected },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, borderColor),
        elevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Hello, world!")
            // AnimatedVisibility as a part of the transition.
            transition.AnimatedVisibility(
                visible = { targetSelected -> targetSelected },
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Text(text = "It is fine today.")
            }
            // AnimatedContent as a part of the transition.
            transition.AnimatedContent { targetState ->
                if (targetState) {
                    Text(text = "Selected")
                } else {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                }
            }
        }
    }
}


@Composable
@Preview
fun rememberInfiniteTransitionTest() {
    val infiniteTransition = rememberInfiniteTransition()

    val color by infiniteTransition.animateColor(
        initialValue = Color.Red, targetValue = Color.Green, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
    )

}

