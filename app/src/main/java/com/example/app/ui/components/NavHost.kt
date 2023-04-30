package com.example.app.ui.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import com.example.app.ui.navigation.Destination
import com.example.app.ui.screens.ArticleDetailScreen
import com.example.app.ui.screens.MainFrame
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * Class:NavHost
 * @author: tangyu
 * Description:
 * @Date:  2023/1/3
 *

 */


/**
 * 导航控制器
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Destination.HomeFrame.route,

        ) {
        //首页大框架
        composable(Destination.HomeFrame.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Companion.Right, tween(400))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Companion.Left,
                    tween(400)
                )
            },
            ) {
            MainFrame() {
                navController.navigate(Destination.ArticleDetail.route)
            }
        }
        //文章详情页
        composable(
            Destination.ArticleDetail.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Companion.Left, tween(400))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Companion.Right,
                    tween(400)
                )
            },
        ) {
            ArticleDetailScreen(){
                navController.popBackStack()
            }
        }

    }
}

