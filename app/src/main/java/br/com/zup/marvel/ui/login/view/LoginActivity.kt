package br.com.zup.marvel.ui.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.marvel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}