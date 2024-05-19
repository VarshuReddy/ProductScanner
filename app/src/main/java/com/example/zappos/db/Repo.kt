package com.example.zappos.db

import androidx.lifecycle.LiveData


class Repo(private val dao:ProductsDao) {



    val listOfProducts:LiveData<List<ProductEntity>> = dao.getAll()

     suspend fun insert(entity: ProductEntity){
        dao.insertAll(entity)
    }

     suspend fun delete(entity: ProductEntity){
        dao.delete(entity)
    }

     suspend fun getProductEntity(id: Int) :ProductEntity{
        return dao.getProduct(id)
    }


}