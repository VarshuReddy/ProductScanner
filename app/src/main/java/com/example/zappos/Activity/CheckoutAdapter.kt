package com.example.zappos.Activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zappos.databinding.ItemListCheckoutBinding

class CheckoutAdapter:Adapter<CheckoutAdapter.CheckOutVH>() {


    private var list: List<ScannedEntity> = listOf()

    inner class CheckOutVH(val binding: ItemListCheckoutBinding) :ViewHolder(binding.root) {
        var name=binding.name
        val qty=binding.qty
        val cost=binding.cost
        val add= binding.addBtn
        val minus=binding.minusBtn

        fun bind(){

        }

    }

    fun submit(list:List<ScannedEntity>){
        this.list= list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutVH {
        val binding=ItemListCheckoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return CheckOutVH(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CheckOutVH, position: Int) {
        val item= list[position]
        holder.name.text=item.productEntity.name
        holder.cost.text=item.productEntity.price.toString()
        holder.qty.text=item.quantity.toString()
        holder.add.setOnClickListener{
            item.quantity.plus(1)
            holder.qty.text=item.quantity.toString()
        }

        holder.minus.setOnClickListener{
            item.quantity.minus(1)
            holder.qty.text=item.quantity.toString()
        }
    }
}