package com.kugemi.lime.presentataion.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.kugemi.lime.R
import com.kugemi.lime.presentataion.resources.*
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(channelsViewModel: ChannelsViewModel) {
    val state = remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
            channelsViewModel.updateSearchQuery(value.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = defaultPadding, vertical = searchTextFieldVerticalPadding),
        textStyle = TextStyle(color = Color.White, fontSize = channelNameTextSize),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(searchIconPadding)
                    .size(searchIconSize)
            )
        },
        placeholder = { Text(text = stringResource(R.string.channel_search_hint)) },
        singleLine = true,
        shape = RoundedCornerShape(searchTextFieldShape),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}