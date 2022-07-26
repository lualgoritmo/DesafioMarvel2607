package br.com.zup.marvel.ui.register.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.USER_KEY
import br.com.zup.marvel.data.model.User
import br.com.zup.marvel.databinding.ActivityRegisterUserBinding
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.register.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterUser : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegisterUser.setOnClickListener {
            val user = getUserTribute()
            viewModel.validateDataUser(user)
        }
        initObservers()
    }

    fun getUserTribute(): User {
        return User(
            name = binding.edtRegisterName.text.toString(),
            email = binding.edtRegisterEmail.text.toString(),
            password = binding.edtRegisterPasswordl.text.toString()
        )
    }
    private fun initObservers() {
        viewModel.registerState.observe(this) {
            goToHome(it)
        }

        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }
    private fun goToHome(user: User) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        startActivity(intent)
    }
}