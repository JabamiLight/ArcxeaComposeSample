package com.example.app.ui.navigation

/**
 * Class:Destination
 * @author: tangyu
 * Description:
 * @Date:  2023/1/3
 *

 */
sealed class Destination (val route:String){
    //首页大框架
    object HomeFrame:Destination("HomeFrame")
    //文章详情页
    object ArticleDetail:Destination("ArticleDetail")
}