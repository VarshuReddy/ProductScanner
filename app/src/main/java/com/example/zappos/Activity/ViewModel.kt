package com.example.zappos.Activity

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zappos.db.ProductEntity
import com.example.zappos.db.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(private  val repo: Repo):ViewModel() {
    private lateinit var list: LiveData<List<ProductEntity>>

    init {
            list = repo.listOfProducts

    }

    fun getTotalList(): LiveData<List<ProductEntity>> {


        return list
    }


    class MyViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }

    }
}