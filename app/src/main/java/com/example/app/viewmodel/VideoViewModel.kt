package com.example.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app.model.entity.VideoEntity

/**
 * Class:VideoViewModel
 * @author: tangyu
 * Description:
 * @Date:  2022/11/29
 *

 */
class VideoViewModel:ViewModel() {
    var list by mutableStateOf(
        listOf(
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
            ),
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner2.webp"
            ),
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner3.webp"
            ),
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner4.jpg"
            ),
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner5.jpg"
            ),
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
            ),
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
            ),
            VideoEntity(
                title = "行测老师告诉你如何制定适合自己的学习方案",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
            )
        )
    )
        private set
}