package br.com.zup.marvel.data.repository

import br.com.zup.marvel.*
import br.com.zup.marvel.data.model.Marvel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MarvelRepository {
    private val auth: FirebaseAuth = Firebase.auth
    fun cadastreUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun upProfileUser(name: String): Task<Void>? {
        val profile = UserProfileChangeRequest.Builder().setDisplayName(name).build()
        return auth.currentUser?.updateProfile(profile)
    }

    fun getMarvelList(): List<Marvel> {
        val marvelList = mutableListOf<Marvel>()

        marvelList.add(
            Marvel(
                R.drawable.gamora,
                GAMORRA_NOME,
                GAMORRA_DESCRICAO
            )
        )

        marvelList.add(
            Marvel(
                R.drawable.homem_ferro,
                HOMEM_FERRO_NOME,
                HOMEM_FERRO_DESCRICAO
            )
        )

        marvelList.add(
            Marvel(
                R.drawable.mulher_invisivel,
                MULHER_INIVISVEL_NOME,
                MULHER_INVISVEL_DESCRICAO
            )
        )

        return marvelList
    }
}