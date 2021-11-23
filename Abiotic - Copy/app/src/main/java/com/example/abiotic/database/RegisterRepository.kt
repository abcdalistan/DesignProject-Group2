package com.example.abiotic.database

class RegisterRepository(private val dao: RegisterDAO) {

    val users = dao.getAllUserInfo()

    suspend fun insert(user: Register) {
        return dao.insertUser(user)
    }

    suspend fun getUserName(userName: String):Register?{
        return dao.getUsername(userName)
    }
}