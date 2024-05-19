package com.example.zappos.Activity


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.zappos.db.ProductEntity
import com.example.zappos.db.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CheckOutVM(repo: Repo) : ViewModel() {


    private val listOfScanned: MutableLiveData<List<ScannedEntity>> = MutableLiveData<List<ScannedEntity>>()

    private var repo: Repo


    init {
        this.repo = repo

    }

    fun forEachId(list: ArrayList<Int>?) = viewModelScope.launch(Dispatchers.IO) {
        val productsScanned = mutableListOf<ProductEntity>()

        val map = mutableMapOf<Int, Int>()

        list?.forEach {
            val existingQty = map[it]
            map[it] = existingQty?.plus(1) ?: 1
        }

        val list = map.map { (id, qty) ->
            ScannedEntity(getProduct(id), qty)
        }.toList()

        withContext(Dispatchers.Main){
            listOfScanned.value = list
        }
    }

    fun getListOfScannedItems(): MutableLiveData<List<ScannedEntity>> {
        return listOfScanned
    }

    suspend fun getProduct(id: Int): ProductEntity {
        return repo.getProductEntity(id)
    }


    class CheckOutVMF(private val repo: Repo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CheckOutVM::class.java)) {
                @Suppress("UNCHECKED_CAST") return CheckOutVM(repo) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
    }
}

data class ScannedEntity(
    val productEntity: ProductEntity, val quantity: Int
)