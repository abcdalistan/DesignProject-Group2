package com.example.abiotic.database

import androidx.room.*


@Entity(tableName = "Users")
data class Register (

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String,


    )