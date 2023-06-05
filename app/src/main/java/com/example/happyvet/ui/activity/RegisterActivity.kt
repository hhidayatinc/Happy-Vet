package com.example.happyvet.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.happyvet.databinding.RegisterLayoutBinding

class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: RegisterLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = RegisterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}