package com.example.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Class:TaskViewModel
 * @author: tangyu
 * Description:
 * @Date:  2022/11/29
 *

 */
class TaskViewModel : ViewModel() {

    var taskDate by mutableStateOf("学习周期:2022.01.01-2022.12.31")

    var pointOfYear by mutableStateOf(10000)


    var pointOfYearPercent by mutableStateOf(0f)

    var totalPointOfYear = 13500


    fun updatePointPercent() {
        pointOfYearPercent = 220f * pointOfYear / totalPointOfYear

    }

    var pointsOfWeek = mutableStateListOf(0f,2f,6f,9.5f,10f,15f,5f)

    //日期
    val weeks = listOf("02.05", "02.06", "02.07", "02.08", "02.09", "02.10", "今日")

}

