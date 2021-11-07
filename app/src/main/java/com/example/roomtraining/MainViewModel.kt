package com.example.roomtraining

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomtraining.model.User
import com.example.roomtraining.room.UserDatabase
import com.example.roomtraining.repositories.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<User>>
    private val errorLiveData = MutableLiveData<String>()
    private val successLiveData = MutableLiveData<String>()
    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun getErrorLiveData() = errorLiveData
    fun getSuccessLiveData() = successLiveData
    fun getReadAllData() = readAllData

    fun insertUser(name: String, ageStr: String){
        if(checkInput(name, ageStr)){
            val user = User(0, name, ageStr.toInt())
            addUser(user)
            successLiveData.value = "Success, user added"
        }
        else{
            errorLiveData.value = "Fill in all the fields"
        }
    }

    private fun addUser(user: User){
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    private fun checkInput(name: String, age: String) =
        name.isNotEmpty() && age.isNotEmpty()

}