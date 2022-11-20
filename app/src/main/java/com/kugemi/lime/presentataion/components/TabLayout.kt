package com.kugemi.lime.presentataion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.kugemi.lime.presentataion.screen.ChannelsScreen
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabLayout(channelsViewModel: ChannelsViewModel) {

    val pagerState = rememberPagerState(pageCount = 2)

    Column {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, channelsViewModel)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Все",
        "Избранные",
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color(0xFF343438),
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color(0xFF0077FF)
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, channelsViewModel: ChannelsViewModel) {
    HorizontalPager(
        state = pagerState,
        verticalAlignment = Alignment.Top
    ) {
            page ->
        when (page) {
            0 -> ChannelsScreen(channelsViewModel)
            1 -> ChannelsScreen(channelsViewModel, true)
        }
    }
}