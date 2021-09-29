package com.example.app_final

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.app_final.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var toolbar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = supportActionBar!!
        /*notification()
        firebaseAnaylitics()*/
        setUp()

    }

    private fun setUp() {
        hideToolbar()
        hideNavigation()

        val navView: BottomNavigationView = binding.shopBottomNavigation
        val navController = findNavController(R.id.shop_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.productsFragment,
                R.id.favoritesFragment,
                R.id.profileFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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

    fun visibleToolbar() {
        toolbar.show()
    }

    private fun hideToolbar() {
        toolbar.hide()
    }

    fun visibleNavigation() {
        binding.shopBottomNavigation.visibility = View.VISIBLE
    }

    fun hideNavigation() {
        binding.shopBottomNavigation.visibility = View.GONE
    }
}