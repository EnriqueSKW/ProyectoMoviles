package com.example.meetandfix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meetandfix.R

class CustomerProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_profile, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            CustomerProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}