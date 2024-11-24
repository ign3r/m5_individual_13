package com.example.m5individual13.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.m5individual13.view.DetailView
import com.example.m5individual13.view.HomeView
import com.example.m5individual13.viewmodel.IMCViewModel


@Composable
fun NavManager (viewModel: IMCViewModel,context: Context){
    val navController= rememberNavController()
    NavHost(navController, startDestination = "Home" ){
        composable("Home") {
            HomeView(viewModel, navController,context)
        }
        composable("DetailView") {
            DetailView(viewModel,navController)
        };

    }
}