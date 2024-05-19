package com.example.zappos.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAll(): LiveData<List<ProductEntity>>

    @Insert
    suspend fun insertAll(vararg products: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    suspend fun getProduct(id: Int): ProductEntity

}