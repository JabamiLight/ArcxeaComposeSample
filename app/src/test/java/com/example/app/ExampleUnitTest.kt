package com.example.app

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @OptIn(FlowPreview::class)
    @Test
     fun addition_isCorrect() = runBlocking {

         flowOf(1,2,3)
             .flatMapConcat {
                 flowOf(it*it)
             }.collect{
                 println(it)
             }

    }


    suspend fun tt()=withContext(Dispatchers.IO){
        println("1232321")
    }


}