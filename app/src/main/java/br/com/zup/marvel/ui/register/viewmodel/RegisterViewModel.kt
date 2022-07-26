package br.com.zup.marvel.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.*
import br.com.zup.marvel.data.model.User
import br.com.zup.marvel.data.repository.AuthenticationRepository

class RegisterViewModel: ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User) {
        when {
            user.name.isEmpty() -> {
                _errorState.value = NAME_ERROR_MESSAGE
            }
            user.email.isEmpty() -> {
                _errorState.value = EMAIL_ERROR_MESSAGE
            }
            user.password.isEmpty() -> {
                _errorState.value = PASSWORD_ERROR_MESSAGE
            }
            user.name.length in 1..2 -> {
                _errorState.value = TXT_ERROR_QTD
            }
            user.password.length in 1..7 -> {
                _errorState.value = TXT_ERROR_PASSWORD_QTD
            }
            else -> {
                cadastreUser(user)
            }
        }
    }
    private fun cadastreUser(user: User) {
        try {
            authenticationRepository.cadastreUser(
                user.email,
                user.password
            ).addOnSuccessListener {

                authenticationRepository.upProfileUser(user.name)?.addOnSuccessListener {
                    _registerState.value = user
                }

            }.addOnFailureListener {
                _errorState.value = CREATE_USER_ERROR_MESSAGE + it.message
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }

}