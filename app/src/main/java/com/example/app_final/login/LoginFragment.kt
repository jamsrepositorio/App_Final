package com.example.app_final.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.app_final.R
import com.example.app_final.databinding.FragmentLoginBinding
import com.example.app_final.signup.ProviderType
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        setUp()
        return binding.root
     }

    // ahorita lo agregue
    private fun setUp() {
        binding.signUpButton.setOnClickListener {
            if (binding.emailText.text.toString()
                    .isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.editTextPassword.text.toString()
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
                    .isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.emailText.text.toString(),
                    binding.editTextPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showProfile(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
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
        setUpComponent()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpComponent() = with(binding) {
        loginButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_productsFragment,
                null,
                NavOptions.Builder().setPopUpTo(findNavController().graph.startDestination, true)
                    .build()
            )
        }
    }
}