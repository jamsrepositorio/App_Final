package com.example.app_final.signup

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_final.R
import com.example.app_final.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding: FragmentSignupBinding get() = _binding!!

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(layoutInflater)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")

        return binding.root
    }

    fun register(view: View) {
        createNewAccount()
    }

    private fun createNewAccount() {
        val name: String = binding.emailText.text.toString()
        val contra: String = binding.passwordText.text.toString()




        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contra)) {
            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(name, contra)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isComplete) {
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userBD = dbReference.child(user?.uid?:"")

                        userBD.child("Name").setValue(name)
                        userBD.child("Contraseña").setValue(contra)
                    }
                }

        }
    }

    private fun verifyEmail(user: FirebaseUser?) {
        user?.sendEmailVerification()
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isComplete) {
                    Toast.makeText(requireContext(), "Email enviado", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Error al enviar", Toast.LENGTH_LONG).show()
                }
            }

    }

    /*private fun action() {
        startActivity(Intent(this.LoginFragment::class.java))
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
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
}