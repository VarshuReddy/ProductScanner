package com.example.zappos.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.example.zappos.R
import com.example.zappos.databinding.MainActivityBinding
import com.example.zappos.databinding.QrActivityBinding

class QRActivity:AppCompatActivity() {
    private lateinit var binding: QrActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.qr_activity)
        addFragments()
    }

    private fun addFragments() {
        val qr=QRGenerator()
        val transaction:FragmentTransaction=supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_view,qr," ").commit()

    }
}