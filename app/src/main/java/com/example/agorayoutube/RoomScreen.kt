package com.example.agorayoutube

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button

@Composable
fun RoomScreen(
    onNavigate:(String)->Unit,
    viewModel:RoomViewModel= androidx.lifecycle.viewmodel.compose.viewModel()
){
    LaunchedEffect(key1 = true){
        viewModel.onJoinEvent.collectLatest { name ->
            onNavigate("video_screen/$name")
        }
    }
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ){
        TextField(
            value = viewModel.roomName.value.text,
            onValueChange =viewModel::onRoomEnter,
            modifier=Modifier.fillMaxWidth(),
            isError = viewModel.roomName.value.error != null,
            placeholder = {
                Text(text = "Enter a room name")
            }
        )
        viewModel.roomName.value.error?.let {
            Text(text = it,color= MaterialTheme.colors.error)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick=viewModel::onJoinRoom){
            Text(text = "Join")
        }

    }
}