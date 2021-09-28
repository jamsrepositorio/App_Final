package com.example.app_final.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_final.MainActivity
import com.example.app_final.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).visibleToolbar()
        (requireActivity() as MainActivity).visibleNavigation()
        setUpComponent()
    }

    private fun setUpComponent() = with(binding) {
        fillProducts()
    }

    private fun fillProducts() {
        products.add(Product("1", "Cartera"))
    }

    private var products = mutableListOf<Product>()
}

data class Product(
    val id: String,
    val nombre: String
)