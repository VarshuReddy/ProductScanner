package com.example.zappos.Activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zappos.db.ProductEntity
import com.example.zappos.db.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel (val repo: Repo):ViewModel()
{

    init {
    }

     fun addProduct(product:ProductEntity){
        viewModelScope.launch(Dispatchers.IO
        ) {
            repo.insert(product)
        }

    }


    class MyAddViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }

    }
}