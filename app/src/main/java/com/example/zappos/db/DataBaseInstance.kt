package com.example.zappos.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProductEntity::class), version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getDao() : ProductsDao

    //static
    companion object{

        @Volatile
        private var INSTANCE:ProductDatabase?=null

        fun getDatabase(context: Context):ProductDatabase{

        //returns INSTANCE if not null
            return INSTANCE?: synchronized(this){

                val instance =Room.databaseBuilder(context.applicationContext,
                    ProductDatabase::class.java,
                    "zap_database"
                ).build()

                INSTANCE=instance

                //return instance
                instance
            }
        }


    }


}
