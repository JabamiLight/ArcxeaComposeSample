package mvi.ui

/**
 * Class:TestMviPage
 * @author: tangyu
 * Description:
 * @Date:  2023-05-01
 *

 */
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import mvi.state.WhatsAppUserUiState
import mvi.viewmodel.WhatsAppCallsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TestMviPage(viewmodel: WhatsAppCallsViewModel= viewModel()) {

    val whatsAppUsersUiState by viewmodel.whatsAppUserState.collectAsStateWithLifecycle()
    Log.d("tedu","重组")
    extracted(whatsAppUsersUiState)
}

@Composable
private fun extracted(whatsAppUsersUiState: WhatsAppUserUiState) {
    when (whatsAppUsersUiState) {
        WhatsAppUserUiState.Loading -> Text("加载中")
        WhatsAppUserUiState.Error -> Text("加载失败")
        is WhatsAppUserUiState.Success -> {
            Text("加载成功${whatsAppUsersUiState.data}")
        }
    }
}

@Preview
@Composable
fun TestMviPagePreview() {
    TestMviPage()
}

