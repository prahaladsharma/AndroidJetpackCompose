package com.example.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.features.newsDetails.NewDetailsScreen
import com.example.util.Constants.DETAILS_ROUTE
import com.example.util.Constants.ID

fun NavController.navigateToDetails(id:String){
    val route = "${Screen.Details}/$id"
    navigate(route)
}
fun NavGraphBuilder.topNewDetailsScreen(onBack: () -> Unit){
    composable(
        route = DETAILS_ROUTE,
        arguments =
            listOf(
                navArgument(name = ID){
                    type = NavType.StringType
                },
            ),
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getString(ID).orEmpty()
        NewDetailsScreen(id = id){
            onBack()
        }
    }
}