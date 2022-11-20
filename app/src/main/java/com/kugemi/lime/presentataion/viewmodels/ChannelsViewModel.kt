package com.kugemi.lime.presentataion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kugemi.lime.domain.model.Channels
import com.kugemi.lime.domain.useCase.ChannelsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelsViewModel @Inject constructor(
    channelsUseCase: ChannelsUseCases
) : ViewModel() {
    private val myChannels = MutableLiveData<Channels>()

    val channels: LiveData<Channels>
        get() = myChannels

    init {
        viewModelScope.launch(Dispatchers.IO) {
            myChannels.postValue(channelsUseCase.getChannelsUseCase())
        }
    }
}