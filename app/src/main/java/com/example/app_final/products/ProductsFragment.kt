package com.example.app_final.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_final.MainActivity
import com.example.app_final.databinding.FragmentProductsBinding

class ProductsFragment : Fragment(), ProductClick {
    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding get() = _binding!!
    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        if (recyclerProducts.adapter == null) {
            recyclerProducts.adapter = productAdapter
        }
        productAdapter.submitList(fillProducts())
    }

    private fun fillProducts(): List<ProductViewData> {
        return listOf(
            ProductViewData(1f, "Producto 1", "Es es el producto 1"),
            ProductViewData(2f, "Producto 2", "Es es el producto 2"),
            ProductViewData(3f, "Producto 3", "Es es el producto 3"),
            ProductViewData(4f, "Producto 4", "Es es el producto 4")
        )
    }

    override fun onClickProduct(productViewData: ProductViewData) {
        println("ClickProduct ${productViewData.id}")
    }
}