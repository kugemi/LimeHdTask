package com.kugemi.lime.presentataion.screen

import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kugemi.lime.domain.model.Channel
import com.kugemi.lime.presentataion.components.SearchComponent
import com.kugemi.lime.presentataion.components.TabLayout
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    //val channelsViewModel: ChannelsViewModel = viewModel()

    //val channels = channelsViewModel.channels.observeAsState()



    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF343438))
        ) {
            SearchComponent(textState)
        }

        TabLayout()

    }
}