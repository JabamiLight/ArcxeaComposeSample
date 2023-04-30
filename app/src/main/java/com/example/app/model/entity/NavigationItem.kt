package com.example.app.model.entity

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Class:NavigationItem 导航栏实体类
 * @author: tangyu
 * Description:
 * @Date:  2022/10/19
 *

 */
data class NavigationItem(

    val title:String,//导航栏的标题
    val icon:ImageVector//导航栏的图标
)
