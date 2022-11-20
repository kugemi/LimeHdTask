package com.kugemi.lime.presentataion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.kugemi.lime.domain.model.Channel

@Composable
fun ChannelItem(channel: Channel) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF343438),
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(channel.image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 16.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = channel.name_ru,
                    color = Color.White,
                    fontSize = 18.sp
                )
                Text(
                    text = channel.current.title,
                    color = Color(0xFFE9EAEF),
                    fontSize = 15.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint = Color(0xFFD1D5DF),
                        contentDescription = "contentDescription",
                    )
                }
            }
        }
    }
}