package com.example.app_final.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.app_final.databinding.ProductHolderBinding


class ProductAdapter(val productClick: ProductClick) :
    ListAdapter<ProductViewData, ProductAdapter.ProductViewHolder>(COMPARATOR) {

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<ProductViewData>() {
            override fun areItemsTheSame(
                oldItem: ProductViewData,
                newItem: ProductViewData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductViewData,
                newItem: ProductViewData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ProductViewHolder(private val binding: ProductHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductViewData) = product.apply {
            binding.root.setOnClickListener {
                productClick.onClickProduct(this)
            }
            binding.titulo.text = this.nombreProducto
            binding.description.text = this.descripcionProducto
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(parent.createViewBinding())

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inline fun <reified Binding : ViewBinding> ViewGroup.createViewBinding(): Binding {
        val inflateMethod = Binding::class.java.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return inflateMethod.invoke(null, LayoutInflater.from(context), this, false) as Binding
    }
}