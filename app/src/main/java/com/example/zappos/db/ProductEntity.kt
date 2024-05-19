package com.example.zappos.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(true) val id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo (name = "price") val price: Double
)