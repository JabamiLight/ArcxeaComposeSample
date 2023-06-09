package com.example.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app.model.entity.ArticleEntity

/**
 * Class:AirticleViewModel
 * @author: tangyu
 * Description:
 * @Date:  2022/11/28
 *

 */
class ArticleViewModel: ViewModel() {

    //文章列表数据
    var list by mutableStateOf(
        listOf(
            ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ),
            ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            ), ArticleEntity(
                title = "人社部向疫情防控期参与复工复产的劳动者表",
                source = "“学习强国”学习平台",
                timestamp = "2020-02-10"
            )
        )
    )


}