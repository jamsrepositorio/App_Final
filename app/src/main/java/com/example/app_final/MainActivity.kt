package com.example.app_final

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.app_final.databinding.ActivityMainBinding
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*notification()
        firebaseAnaylitics()*/
        // setUp()
    }

   /* private fun showProfile(email: String, provider: ProviderType)
    {
        val profileIntent = Intent(this, ProfileActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(profileIntent)
    }*/

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
   dialog.show()
    }

 /*   private fun setUp() {
        title = "Autentication"

        binding.signUpButton.setOnClickListener {
            if (binding.emailText.text.toString()
                    .isNotEmpty() && binding.passwordText.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.passwordText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showProfile(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        binding.loginButton.setOnClickListener {
            if (binding.emailText.text.toString()
                    .isNotEmpty() && binding.passwordText.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.passwordText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showProfile(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

//        binding.facebookButton.setOnClickListener {
//            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
//            LoginManager.getInstance().registerCallback(
//                callbackManager,
//                object : FacebookCallback<LoginResult> {
//                    override fun onSuccess(result: LoginResult?) {
//                        result?.let {
//                            val token = it.accessToken
//                            val credential = FacebookAuthProvider.getCredential(token.token)
//                            FirebaseAuth.getInstance().signInWithCredential(credential)
//                                .addOnCompleteListener {
//                                    if (it.isSuccessful) {
//                                        showProfile(
//                                            it.result?.user?.email ?: "",
//                                            ProviderType.FACEBOOK
//                                        )
//                                    } else {
//                                        showAlert()
//                                    }
//                                }
//                        }
//                    }
//
//                    override fun onCancel() {}
//
//                    override fun onError(error: FacebookException?) {
//                        showAlert()
//                    }
//
//                }
//            )
//        }
    }*/
}