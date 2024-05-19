package com.example.zappos.Activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zappos.databinding.CheckoutFrgamentBinding
import com.example.zappos.db.ProductDatabase
import com.example.zappos.db.Repo
import java.util.ArrayList

class CheckOutFragment:Fragment() {
    private lateinit var adapter: CheckoutAdapter
    private var list: ArrayList<Int>? = arrayListOf()
    private lateinit var binding: CheckoutFrgamentBinding
    private lateinit var context:Context
    private lateinit var viewModel:CheckOutVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CheckoutFrgamentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao= ProductDatabase.getDatabase(requireActivity()).getDao()
        viewModel=ViewModelProvider(this,CheckOutVM.CheckOutVMF(Repo(dao))).get(CheckOutVM::class.java)
        list=arguments?.getIntegerArrayList("idList")
        adapter= CheckoutAdapter()
        viewModel.forEachId(list)

        binding.rvCheckout.adapter= adapter
        binding.rvCheckout.layoutManager=LinearLayoutManager(getContext())

        viewModel.getListOfScannedItems().observe(viewLifecycleOwner, Observer {
            adapter.submit(it)
        })
    }

}