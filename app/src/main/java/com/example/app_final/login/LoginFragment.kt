package com.example.app_final.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.app_final.R
import com.example.app_final.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!


    private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)


        auth = FirebaseAuth.getInstance()

        return binding.root
    }


    private fun loginUser(){
        val user:String= binding.emailText.text.toString()
        val contra:String=binding.editTextPassword.text.toString()


        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(contra)){
            binding.progressBar.visibility=View.VISIBLE

            auth.signInWithEmailAndPassword(user,contra)
                .addOnCompleteListener(requireActivity()){
                    task ->
                    if(task.isSuccessful){
                        action()
                    }else{
                        Toast.makeText(requireContext(),"Error en la autenticacion", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun action(){

    }

    fun login(view:View){
        loginUser()
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