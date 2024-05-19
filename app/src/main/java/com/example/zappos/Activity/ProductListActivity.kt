package com.example.zappos.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zappos.Activity.MyViewModel.MyViewModelFactory
import com.example.zappos.R
import com.example.zappos.databinding.ActivityPListBinding
import com.example.zappos.db.ProductDatabase
import com.example.zappos.db.ProductEntity
import com.example.zappos.db.Repo

class ProductListActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPListBinding

    private val adapter: ProductListAdapter = ProductListAdapter()
    private lateinit var submitList:List<ProductEntity>
    private lateinit var viewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_p_list)
        submitList= listOf()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
        val dao= ProductDatabase.getDatabase(this).getDao()
        viewModel=ViewModelProvider(this, MyViewModelFactory(Repo(dao))).get(MyViewModel::class.java)
        viewModel.getTotalList().observe(this) { list ->
            list?.let {
                submitList = list
                adapter.submitList(submitList,baseContext)
            }
        }


        binding.floatingActionButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v) {
            binding.floatingActionButton-> {
                val intent = Intent(this, AddProductsActivity::class.java)
                startActivity(intent)
            }
        }


    }
}

