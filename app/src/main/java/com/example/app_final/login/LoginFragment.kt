package com.example.app_final.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.app_final.R
import com.example.app_final.data.ProviderType
import com.example.app_final.data.UserPreferences
import com.example.app_final.data.UserPreferences.EMPTY_STRING
import com.example.app_final.data.UserPreferences.GOOGLE_CODE
import com.example.app_final.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        setUp()
        return binding.root
    }

    // ahorita lo agregue
    private fun setUp() = with(binding) {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)

        loginButton.setOnClickListener {
            if (binding.emailText.text.toString()
                    .isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.editTextPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        UserPreferences.saveCredential(
                            requireContext(),
                            binding.emailText.text.toString()
                        )
                        findNavController().navigate(
                            R.id.action_loginFragment_to_productsFragment,
                            null,
                            NavOptions.Builder()
                                .setPopUpTo(findNavController().graph.startDestination, true)
                                .build()
                        )
                    } else {
                        showAlert()
                    }
                }
            }
        }

        googleButton.setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_CODE) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            googleResult(task)
        }
    }

    private fun googleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    account.email,
                    EMPTY_STRING
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        UserPreferences.saveCredential(
                            requireContext(),
                            binding.emailText.text.toString()
                        )
                        findNavController().navigate(
                            R.id.action_loginFragment_to_productsFragment,
                            null,
                            NavOptions.Builder()
                                .setPopUpTo(findNavController().graph.startDestination, true)
                                .build()
                        )
                    } else {
                        showAlert()
                    }
                }
            }
        } catch (e: ApiException) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    //ahorita lo agregue
    private fun showAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Acepter", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    //lo agregue ahorita
    private fun showProfile(email: String, provider: ProviderType) {
        /*val profileIntent = Intent(this, ProfileActivityll::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(profileIntent)*/
    }


/*//lo agregue ayer
fun goToHome(view:View)
{
if(loginValido())
{
    val intent = Intent(requireActivity(), ProductsFragment::class.java).apply {
        putExtra("Email", emailText.text.toString())
    }
    startActivity(intent)
}
else
{
    Toast.makeText(view.context,"Faltan datos por ingresar", Toast.LENGTH_LONG).show()
}

}


fun loginValido(): Boolean {
if (emailText.text.toString().isNotEmpty() && editTextPassword.text.toString().isNotEmpty()
) {
    return true
}

return false
}
*/
/*private fun loginUser() {
    val user: String = binding.emailText.text.toString()
    val contra: String = binding.editTextPassword.text.toString()


//        para validar que no quede vacío
    if (user.isNotEmpty() && contra.isNotEmpty()) {
        binding.progressBar.visibility = View.VISIBLE

        auth.signInWithEmailAndPassword(user, contra)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    action()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error en la autenticacion",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}*/

/*private fun action() {

}

fun login(view: View) {
    loginUser()
}*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}