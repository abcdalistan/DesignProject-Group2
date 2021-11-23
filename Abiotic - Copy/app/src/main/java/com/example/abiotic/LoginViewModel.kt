package com.example.abiotic

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.abiotic.database.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    val users = repository.users

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorUsername = MutableLiveData<Boolean>()

    val errorUsername: LiveData<Boolean>
        get() = _errorUsername

    private val _InvalidPassword = MutableLiveData<Boolean>()

    val errorInvalidPassword: LiveData<Boolean>
        get() = _InvalidPassword

    private val _correct = MutableLiveData<Boolean>()
    val correct: LiveData<Boolean>
        get() = _correct


    fun login() {
        if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true

        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(userName = inputUsername.value!!)
                if (usersNames != null) {
                    if (usersNames.password == inputPassword.value) {
                        inputUsername.value = null
                        inputPassword.value = null
                        _correct.value = true


                    } else {
                        _InvalidPassword.value = true
                    }
                } else {
                    _errorUsername.value = true
                }
            }
        }
    }


    fun home() {
        _correct.value = false
    }
    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Working! ")
    }

    fun errorUsername() {
        _errorUsername.value = false
        Log.i("MYTAG", "Working! ")
    }

    fun invalidPassword() {
        _InvalidPassword.value = false
        Log.i("MYTAG", "Working! ")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}