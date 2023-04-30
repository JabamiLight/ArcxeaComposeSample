package com.example.app.ui.components

/**
 * Class:WebView
 * @author: tangyu
 * Description:
 * @Date:  2023/1/22
 *

 */
import android.webkit.WebView
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebView(state: WebViewState) {

    val s by remember {
        mutableStateOf(0)
    }
    AndroidView(factory = { context ->
        WebView(context)
    }, update = {
        when(val content=state.content){
            is WebContent.url->{
                val url=content.url


            }
        }
        it.loadUrl("https://www.baidu.com")
    })
}

sealed class WebContent(){
    data class url(var url:String):WebContent()
    data class data(var data:String,val baseUrl:String):WebContent()

}
class WebViewState(webContent:WebContent){
    //网页内容：url 或者 data(html内容）
    var content by mutableStateOf(webContent)
    var pageTItle:String? by mutableStateOf(null)
}
@Composable
fun rememberWebViewState(url:String)= remember (key1 = url){
    WebViewState(WebContent.url(url))
}

@Composable
fun rememberWebViewState(data:String,baseUrl: String)= remember (key1 = data, key2 =baseUrl){
    WebViewState(WebContent.data(data,baseUrl))
}
@Preview
@Composable
fun WebViewPreview() {
}

