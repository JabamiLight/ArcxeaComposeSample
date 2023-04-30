package com.example.app.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app.model.entity.Categor
import com.example.app.model.entity.DataType
import com.example.app.model.entity.SwiperEntity
import java.util.Locale.Category

/**
 * Class:MainVIewmodel
 * @author: tangyu
 * Description:
 * @Date:  2022/11/8
 *

 */
class MainViewModel : ViewModel() {
    //分类数据
    val category = mutableStateListOf<Categor>(
        Categor("思想政治"),
        Categor("法律法规"),
        Categor("职业道德"),
        Categor("诚信自律")
    )
    var categoryIndex by mutableStateOf(0)

    val types = mutableStateListOf<DataType>(
        DataType("相关资讯", Icons.Default.Description),
        DataType("视频课程", Icons.Default.SmartDisplay)
    )
    var typeIndex by mutableStateOf(0)

    val swiperData =
        mutableListOf(
            SwiperEntity("https://docs.bughub.icu/compose/assets/banner1.webp"),
            SwiperEntity("https://docs.bughub.icu/compose/assets/banner2.webp"),
            SwiperEntity("https://docs.bughub.icu/compose/assets/banner3.webp"),
            SwiperEntity("https://docs.bughub.icu/compose/assets/banner4.jpg"),
            SwiperEntity("https://docs.bughub.icu/compose/assets/banner5.jpg"),
        )

    //通知数据
    val notifications =
        listOf("人社部向疫情防控期", "湖北黄冈新冠肺炎患者治愈病例破千连续5治愈病例破千连续5", "安徽单日新增确诊病例首次降至个位数累计")


}