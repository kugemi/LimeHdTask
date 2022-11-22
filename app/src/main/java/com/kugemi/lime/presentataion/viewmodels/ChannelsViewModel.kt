package com.kugemi.lime.presentataion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kugemi.lime.domain.model.Channel
import com.kugemi.lime.domain.model.Channels
import com.kugemi.lime.domain.useCase.ChannelsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelsViewModel @Inject constructor(
    private val channelsUseCase: ChannelsUseCases
) : ViewModel() {
    private val myChannels = MutableLiveData<Channels?>()

    val channels: LiveData<Channels?>
        get() = myChannels

    private val mySearchQuery = MutableLiveData<String>()

    val searchQuery: LiveData<String>
        get() = mySearchQuery

    private val myCurrentUrl = MutableLiveData<Channel>()

    val currentChannel: LiveData<Channel>
        get() = myCurrentUrl

    init {
        viewModelScope.launch(Dispatchers.IO) {
            myChannels.postValue(channelsUseCase.getChannelsUseCase())
        }
    }

    fun updateSearchQuery(value: String) {
        mySearchQuery.postValue(value)
    }

    fun setCurrentUrl(channel: Channel) {
        myCurrentUrl.postValue(channel)
    }

    fun removeCurrentUrl() {
        myCurrentUrl.postValue(null)
    }
}