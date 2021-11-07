package com.example.roomtraining.repositories

import androidx.lifecycle.LiveData
import com.example.roomtraining.model.User
import com.example.roomtraining.room.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}