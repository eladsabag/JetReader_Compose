package com.elads.booksapp.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elads.booksapp.model.LoadingState
import com.elads.booksapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        viewModelScope.launch {
            if (_loading.value == false) {
                _loading.value = true
                try {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val displayName = task.result.user?.email?.split("@")?.get(0)
                                createUser(displayName)
                                home()
                            } else {
                                Log.d("FB", "createUserWithEmailAndPassword: ${task.result}")
                            }
                            _loading.value = false
                        }
                } catch (ex: Exception) {
                    Log.d("FB", "createUserWithEmailAndPassword: ${ex.message}")
                }
            }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = UserModel(userId.toString(), displayName.toString(), "", "This is a quote", "Software Engineer").toMap()
        FirebaseFirestore.getInstance().collection("users").add(user)
    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            home()
                        } else {
                            Log.d("FB", "signInWithEmailAndPassword: ${task.result}")
                        }
                    }
            } catch (ex: Exception) {
                Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
            }
        }
    }
}