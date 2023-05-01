package demo

/**
 * Class:RememberUpdateState
 * @author: tangyu
 * Description:
 * @Date:  2023-05-01
 *

 */
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RememberUpdateState() {
    var myInput by remember {
        mutableStateOf(0)
    }
    OutlinedButton(
        onClick = {
            myInput++
        }
    ) {
        Text("Increase $myInput")
    }
    Calculation(input = myInput)
}


@Preview
@Composable
fun RememberUpdateStatePreview() {
    RememberUpdateState()
}


@Composable
private fun Calculation(input: Int) {
    val rememberUpdatedStateInput by rememberUpdatedState(input)
    val rememberedInput = remember { input }
    Text("updatedInput: $rememberUpdatedStateInput, rememberedInput: $rememberedInput")
}

    