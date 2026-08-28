package com.vibhorpatil.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vibhorpatil.newsapp.ui.HomeScreenRoute
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.CRITERIA_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.HEADLINE_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.HOME_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.SEARCH_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.search.SearchRoute
import kotlinx.coroutines.CoroutineScope

@Composable
fun NewsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = HOME_SCREEN_ROUTE,
    navigationAction: NavigationAction = remember(navController) {
        NavigationAction(navController)
    }
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = HOME_SCREEN_ROUTE,
        ) {
            HomeScreenRoute(navigationAction)
        }
        composable(
            route = HEADLINE_SCREEN_ROUTE,
        ) {
        }

        composable(
            route = CRITERIA_SCREEN_ROUTE,
        ) {
        }

        composable(
            route = SEARCH_SCREEN_ROUTE
        ) {
            SearchRoute()
        }

    }
}
