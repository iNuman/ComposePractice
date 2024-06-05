package com.example.composepractice

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.composepractice.screens.HomeScreen
import com.example.composepractice.screens.ProfileScreen
import com.example.composepractice.screens.SettingsScreen
import androidx.navigation.compose.NavHost

@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}
