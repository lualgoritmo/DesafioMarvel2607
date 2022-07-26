package br.com.zup.marvel.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.USER_KEY
import br.com.zup.marvel.data.model.User
import br.com.zup.marvel.databinding.ActivityLoginBinding
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.login.viemodel.LoginViewModel
import br.com.zup.marvel.ui.register.view.RegisterUser
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.txtToRegister?.setOnClickListener {
            initRegister()
        }
        binding.btnLoginUser.setOnClickListener {
            val user = getUserData()
            viewModel.validateUserData(user)
        }
        initObserve()
    }

    private fun initRegister() {
        startActivity(Intent(this, RegisterUser::class.java))
    }

    private fun getUserData(): User {
        return User(
            email = binding.edtEmail?.text.toString(),
            password = binding.edtPassword?.text.toString())
    }

    private fun homeGoTo(user: User) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        startActivity(intent)
    }

    private fun initObserve() {
        viewModel.userLoginTo.observe(this) {
            homeGoTo(it)
        }
        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }
}