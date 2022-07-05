package com.example.android.mozper.presentation.login

import androidx.lifecycle.LiveData
import com.example.android.mozper.domain.login.HasSessionActiveUseCase
import com.example.android.mozper.domain.login.LoginUseCase
import com.example.android.mozper.presentation.base.MozperViewModel
import com.example.android.mozper.presentation.base.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val hasSessionActiveUseCase: HasSessionActiveUseCase
) :
    MozperViewModel() {

    private val _loginLiveData = SingleLiveEvent<Boolean>()
    val loginLiveData: LiveData<Boolean> get() = _loginLiveData

    fun validateSession() {
        launchIo {
            _loginLiveData.postValue(hasSessionActiveUseCase.run())
        }
    }

    fun login(user: String?, password: String?) {
        launchIo {
            _loginLiveData.postValue(loginUseCase.run(user, password))
        }
    }
}