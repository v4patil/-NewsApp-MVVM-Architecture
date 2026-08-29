package com.vibhorpatil.newsapp.ui.navigation

import androidx.navigation.NavController
import com.vibhorpatil.newsapp.ui.navigation.NewsAppArgs.FILTER_BY
import com.vibhorpatil.newsapp.ui.navigation.NewsAppArgs.FILTER_BY_ID
import com.vibhorpatil.newsapp.ui.navigation.NewsAppDestination.SEARCH_SCREEN_ROUTE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppScreen.CRITERIA
import com.vibhorpatil.newsapp.ui.navigation.NewsAppScreen.HEADLINE
import com.vibhorpatil.newsapp.ui.navigation.NewsAppScreen.HOME
import com.vibhorpatil.newsapp.ui.navigation.NewsAppScreen.SEARCH
import com.vibhorpatil.newsapp.utils.AppConstant.BY_COUNTRY
import com.vibhorpatil.newsapp.utils.AppConstant.COUNTRY

private object NewsAppScreen {
    const val HOME = "HOME"
    const val HEADLINE = "HEADLINE"
    const val CRITERIA = "CRITERIA"
    const val SEARCH = "SEARCH"
}

object NewsAppArgs {
    const val FILTER_BY = "filterBy"
    const val FILTER_BY_ID = "filterById"
}

object NewsAppDestination {
    const val HOME_SCREEN_ROUTE = HOME
    const val HEADLINE_SCREEN_ROUTE = "$HEADLINE?$FILTER_BY={$FILTER_BY}&$FILTER_BY_ID={$FILTER_BY_ID}"
    const val CRITERIA_SCREEN_ROUTE = "$CRITERIA?$FILTER_BY={$FILTER_BY}"
    const val SEARCH_SCREEN_ROUTE = SEARCH
}

class NavigationAction(private val navController: NavController) {

    fun navigateToHeadlineScreen(filterBy: Int = BY_COUNTRY, filterById : String = COUNTRY) {
        navController.navigate("$HEADLINE?$FILTER_BY=$filterBy&$FILTER_BY_ID=$filterById")
    }

    fun navigateToCriteriaScreen(filterBy: Int) {
        navController.navigate("$CRITERIA?$FILTER_BY=$filterBy")
    }

    fun navigateToSearchScreen() {
        navController.navigate(SEARCH_SCREEN_ROUTE)
    }

}