package com.example.abiotic.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegisterDAO {

    @Query("SELECT * FROM Users ORDER BY userId DESC")
    fun getAllUserInfo(): LiveData<List<Register>>

    @Insert
    suspend fun insertUser(user: Register)

    @Delete
    fun deleteUser(user: Register)

    @Query("SELECT * FROM Users WHERE username LIKE :userName")
    suspend fun getUsername(userName: String): Register?





}