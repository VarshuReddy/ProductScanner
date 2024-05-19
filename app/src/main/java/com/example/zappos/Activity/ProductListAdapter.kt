package com.example.zappos.Activity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zappos.databinding.LayoutProductItemBinding
import com.example.zappos.db.ProductEntity

class ProductListAdapter() : Adapter<ProductListAdapter.ProductItemViewHolder>() {
    private var list: List<ProductEntity> = listOf()

    lateinit var context:Context
    inner class ProductItemViewHolder(binding: LayoutProductItemBinding) : ViewHolder(binding.root) {
        val id = binding.idNo
        val name = binding.name
        val price = binding.price
    }

    fun submitList(newList: List<ProductEntity>, baseContext: Context) {
        list = newList
        this.context=baseContext
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = LayoutProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ProductItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val product = list.get(position)
        holder.name.text = product.name
        holder.id.text = product.id.toString()
        holder.price.text = product.price.toString()
    }

}