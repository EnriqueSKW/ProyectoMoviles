package com.example.meetandfix.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.meetandfix.R
import com.example.meetandfix.SearchAdapter.ShopAdapter
import com.example.meetandfix.SearchAdapter.ShopModel

class CustomerSearchFragment : Fragment(),ShopAdapter.ClickListener {

    //instancial los fragmentos
    private val customerStoreFragment = CustomerStoreFragment()
    var bundle = Bundle()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_search, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recycler= view?.findViewById<RecyclerView>(R.id.Search_RecyclerID)
        recycler?.adapter = ShopAdapter(listOf(
            ShopModel(0,"Store1","direccion1","https://loremflickr.com/320/240"),
            ShopModel(0,"Store2","direccion2","https://loremflickr.com/320/240"),
            ShopModel(0,"Store3","direccion3","https://loremflickr.com/320/240"),
            ShopModel(0,"Store4","direccion4","https://loremflickr.com/320/240"),
            ShopModel(0,"Store5","direccion5","https://loremflickr.com/320/240"),
            ShopModel(0,"Store6","direccion6","https://loremflickr.com/320/240")

        ),this)
        //ir al fragment de la vista del negocio
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CustomerSearchFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    //Función para cambiar el fragmeto actual
    private fun nextFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper, fragment)
            val commit = commit()
        }

    override fun ClickedItem(shop: ShopModel) {
        bundle.putString("nombre", shop.nombre);
        bundle.putString("direccion", shop.direccion);

        customerStoreFragment.arguments=bundle;
        nextFragment(customerStoreFragment)
    }

}