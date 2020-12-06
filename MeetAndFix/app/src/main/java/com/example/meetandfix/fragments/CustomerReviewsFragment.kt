package com.example.meetandfix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meetandfix.R
import kotlinx.android.synthetic.main.fragment_customer_reviews.*
import kotlinx.android.synthetic.main.fragment_customer_search.*


class CustomerReviewsFragment : Fragment() {

    //instancial los fragmentos
    private val customerWriteReviewFragment = CustomerWriteReviewFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_reviews, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // super.onCreate(savedInstanceState)

        this.btnEscribirReseñaCliente.setOnClickListener {
            nextFragment(customerWriteReviewFragment)
        }


    }


    companion object {
        fun newInstance(param1: String, param2: String) =
            CustomerReviewsFragment().apply {

            }
    }

    //Función para cambiar el fragmeto actual
    private fun nextFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper, fragment)
            val commit = commit()
        }
}