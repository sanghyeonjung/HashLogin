package com.example.hashlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hashlogin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivitdy() {
    private lateinit var binding : ActivityLoginBinding
    private var auth : FirebaseAuth ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.activityLoginRegisterBtn.setOnClickListener { // 회원가입
            register(binding.activityLoginIdEdittext.text.toString(), binding.activityLoginPwEdittext.text.toString())
        }

        binding.activityLoginLoginBtn.setOnClickListener { // 로그인
            login(binding.activityLoginIdEdittext.text.toString(), binding.activityLoginPwEdittext.text.toString())
        }
    }

    private fun register(email : String, password : String)
    {
        if(!email.isEmpty() && !password.isEmpty())
        {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this@LoginActivity, "계정 생성 완료.", Toast.LENGTH_SHORT
                        ).show()
                        Log.d("회원가입","성공")
                        finish()
                    } else{
                        Toast.makeText(this@LoginActivity, "계정 생성 실패", Toast.LENGTH_SHORT).show()
                        Log.d("회원가입", "실패")

                    }
                }
        }
    }
    private fun login(email:String, password : String)
    {
        if(!email.isEmpty() && !password.isEmpty())
        {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        Log.d("로그인", "성공")
                    }
                    else{
                        Toast.makeText(this@LoginActivity, "로그인 실패",Toast.LENGTH_SHORT).show()
                        Log.d("로그인", "실패")
                    }
                }
        }
    }
}