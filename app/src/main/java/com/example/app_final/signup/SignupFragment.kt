package com.example.app_final.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.app_final.R
import com.example.app_final.data.User
import com.example.app_final.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

enum class ProviderType {
    BASIC
}

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding get() = _binding!!
    private var firebaseRealTime =
        FirebaseDatabase.getInstance().getReference(User::class.java.simpleName)
    private var firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun createNewAccount() {
        val email: String = binding.emailText.text.toString()
        val contrasena: String = binding.passwordText.text.toString()


        if (email.isNotEmpty() && contrasena.isNotEmpty()) {
            binding.progressContainer.visibility = View.VISIBLE
            binding.signUpContainer.visibility = View.GONE
            firebaseRealTime.push().setValue(
                User(
                    email = email,
                    password = contrasena
                )
            ).addOnSuccessListener {
                firebaseAuth.createUserWithEmailAndPassword(email, contrasena)
                    .addOnCompleteListener { task ->
                        binding.progressContainer.visibility = View.GONE
                        binding.signUpContainer.visibility = View.VISIBLE
                        if (task.isSuccessful) {
                            findNavController().navigate(
                                R.id.action_signupFragment_to_productFragment,
                                null,
                                NavOptions.Builder()
                                    .setPopUpTo(findNavController().graph.startDestination, true)
                                    .build()
                            )
                        } else {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.label_login_error),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
                .addOnFailureListener {
                    binding.progressContainer.visibility = View.GONE
                    binding.signUpContainer.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.label_signup_error),
                        Toast.LENGTH_LONG
                    ).show()
                }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponent()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpComponent() = with(binding) {
        binding.signUpButton.setOnClickListener {
            createNewAccount()
        }
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
}