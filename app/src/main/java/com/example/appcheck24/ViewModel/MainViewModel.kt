package com.example.appcheck24.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.appcheck24.Repository.MainRepository
import com.example.appcheck24.Util.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}