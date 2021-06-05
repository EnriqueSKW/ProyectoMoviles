package com.example.meetandfix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meetandfix.R
import kotlinx.android.synthetic.main.fragment_repairer_store.*


class RepairerStoreFragment : Fragment() {

    //instanciar los fragmentos
    private val repairerReviewsFragment = RepairerReviewsFragment()
    private val repairerEditStoreFragment = RepairerEditStoreFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repairer_store, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //ir al fragmento de reseñas
        this.btnVerResñasReparador.setOnClickListener {
            nextFragment(repairerReviewsFragment)
        }

        //ir al fragmento de editar información del negocio


    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            RepairerStoreFragment().apply {
                arguments = Bundle().apply {
                    
                }
            }
    }

    //Función para cambiar el fragmeto actual
    private fun nextFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper_repairer, fragment)
            val commit = commit()
        }

}