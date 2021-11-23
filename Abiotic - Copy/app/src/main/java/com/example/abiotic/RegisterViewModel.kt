package com.example.abiotic

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.abiotic.database.Register
import com.example.abiotic.database.RegisterDatabase
import com.example.abiotic.database.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class RegisterViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {
    private var userdata: String? = null

    var userDetailsLiveData = MutableLiveData<Array<Register>>()

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val users = repository.users
    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorUsername = MutableLiveData<Boolean>()

    val errorUsername: LiveData<Boolean>
        get() = _errorUsername

    private val _correct = MutableLiveData<Boolean>()
    val correct: LiveData<Boolean>
        get() = _correct

    fun submitButton() {
        if ( inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    _errorUsername.value = true
                } else {
                    val username = inputUsername.value!!
                    val password = inputPassword.value!!
                    insert(Register(0,  username, password))
                    inputUsername.value = null
                    inputPassword.value = null
                    _correct.value = true

                }
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
    }

    fun donetoast() {
        _errorToast.value = false
    }
    fun userName() {
        _errorToast.value = false

    }
    fun register() {
        _correct.value = false
    }

    private fun insert(user: Register): Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}