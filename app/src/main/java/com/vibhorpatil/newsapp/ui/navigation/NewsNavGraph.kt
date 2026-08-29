package com.vibhorpatil.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vibhorpatil.newsapp.ui.HomeScreenRoute
import com.vibhorpatil.newsapp.ui.navigation.NewsAppArgs.FILTER_BY
import com.vibhorpatil.newsapp.ui.navigation.NewsAppArgs.FILTER_BY_ID
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.CRITERIA_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.HEADLINE_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.HOME_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.SEARCH_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.newscriteria.NewsCriteriaRoute
import com.vibhorpatil.newsapp.ui.search.SearchRoute
import com.vibhorpatil.newsapp.ui.topheadline.TopHeadLineRoute
import com.vibhorpatil.newsapp.utils.AppConstant.COUNTRY
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
            arguments = listOf(
                navArgument(FILTER_BY) { type = NavType.IntType; defaultValue = 1 },
                navArgument(FILTER_BY_ID) { type = NavType.StringType; defaultValue = COUNTRY }
            )
        ) {
            TopHeadLineRoute({navController.popBackStack()})
        }

        composable(
            route = CRITERIA_SCREEN_ROUTE,
            arguments = listOf(
                navArgument(FILTER_BY){ type = NavType.IntType; defaultValue = 0}
            )
        ) {
            NewsCriteriaRoute({navController.popBackStack()}, navigationAction)
        }

        composable(
            route = SEARCH_SCREEN_ROUTE
        ) {
            SearchRoute({navController.popBackStack()})
        }

    }
}
