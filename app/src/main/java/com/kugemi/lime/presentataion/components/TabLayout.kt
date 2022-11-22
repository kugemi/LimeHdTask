package com.kugemi.lime.presentataion.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.*
import com.kugemi.lime.R
import com.kugemi.lime.presentataion.resources.tabLayoutIndicatorHeight
import com.kugemi.lime.presentataion.resources.tabRowBackgroundColor
import com.kugemi.lime.presentataion.resources.tabRowColor
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
        stringResource(R.string.all),
        stringResource(R.string.favorites)
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = tabRowBackgroundColor,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = tabLayoutIndicatorHeight,
                color = tabRowColor
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
    ) { page ->
        when (page) {
            0 -> ChannelsScreen(channelsViewModel)
            1 -> ChannelsScreen(channelsViewModel, true)
        }
    }
}