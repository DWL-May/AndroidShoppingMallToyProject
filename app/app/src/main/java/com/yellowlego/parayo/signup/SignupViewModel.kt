package com.yellowlego.parayo.signup

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yellowlego.parayo.api.ParayoApi
import com.yellowlego.parayo.api.request.SignupRequest
import com.yellowlego.parayo.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SignupViewModel(private val app: Application) : AndroidViewModel(app) {

    val email = MutableLiveData("")
    val name = MutableLiveData("")
    val password = MutableLiveData("")

     fun signup() {
         viewModelScope.launch {
             val request = SignupRequest(email.value, password.value, name.value)
             if (isNotValdSignup(request)) {
                 return@launch
             }

             try {
                 val response = requestSignup(request)
                 onSignupResponse(response)
             } catch (e : Exception) {
                 Toast.makeText(app, "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
             }
         }
    }

    private fun isNotValdSignup(signupRequest: SignupRequest) =
        when {
            signupRequest.isNotValidEmail() -> {
                Toast.makeText(app, "이메일 형식이 정확하지 않습니다.", Toast.LENGTH_LONG).show()
                true
            }

            signupRequest.isNotValidPassword() -> {
                Toast.makeText(app, "비밀번호는 8자 이상 20자 이하로 입력해주세요", Toast.LENGTH_LONG).show()
                true
            }

            signupRequest.isNotValidName() -> {
                Toast.makeText(app, "이름은 2자 이상 20자 이하로 입력해주세요.", Toast.LENGTH_LONG).show()
                true
            }
            else -> false
        }

    private suspend fun requestSignup(request: SignupRequest) =
        withContext(Dispatchers.IO) {
            ParayoApi.instance.signup(request)
        }

    private fun onSignupResponse(response: ApiResponse<Void>) {
        if (response.success) {
            Toast.makeText(app, "회원 가입이 되었습니다. 로그인 후 이용해주세요", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(app, response.message ?: "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
        }
    }
}
