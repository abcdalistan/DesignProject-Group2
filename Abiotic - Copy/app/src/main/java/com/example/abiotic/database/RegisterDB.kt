package com.example.abiotic.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Register::class] ,version = 1)
abstract class RegisterDatabase : RoomDatabase() {

    abstract val registerDAO: RegisterDAO

    companion object {

        @Volatile
        private var INSTANCE: RegisterDatabase? = null

        fun getDatabase(context: Application): RegisterDatabase {

            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RegisterDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration().build()
                INSTANCE =instance
            }
            return instance
        }
    }
}
