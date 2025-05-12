package com.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.features.topNewsHeadline.TopHeadlineScreen

fun NavGraphBuilder.topNewsHeadlineScreen(navigateToDetail: (id: String) -> Unit){
    composable(route = Screen.Home.route){
        TopHeadlineScreen(navigateToDetail = navigateToDetail)
    }
}