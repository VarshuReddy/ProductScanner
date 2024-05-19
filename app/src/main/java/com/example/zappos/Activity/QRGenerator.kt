package com.example.zappos.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.zappos.R
import com.example.zappos.databinding.QrFragmentBinding

class QRGenerator : Fragment(), View.OnClickListener {


    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: QrFragmentBinding
    private var  listOfIds= arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QrFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scanCode()
        binding.buttonSubmit.setOnClickListener(this)

    }

    private fun scanCode() {
        val scanView = binding.codeScannerView
        codeScanner = CodeScanner(requireActivity(), scanView)
        codeScanner.apply {
            camera=CodeScanner.CAMERA_BACK
            formats=CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false
        }

        codeScanner.decodeCallback = DecodeCallback {

            activity?.runOnUiThread {
                listOfIds.add(it.text.toInt())
                Toast.makeText(activity, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
            }
            codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
                activity?.runOnUiThread {
                    Toast.makeText(
                        activity, "Camera initialization error: ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        scanView.setOnClickListener {
            codeScanner.startPreview()
        }




    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    override fun onClick(p0: View?) {
        val bundle=Bundle()
        bundle.putIntegerArrayList("idList",listOfIds)
        val cf=CheckOutFragment()
        cf.arguments=bundle
        val transaction:FragmentTransaction=(activity as QRActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view,cf).commit()

    }


}