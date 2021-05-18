package com.example.meetandfix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.meetandfix.R
import kotlinx.android.synthetic.main.fragment_customer_store.*


class CustomerStoreFragment : Fragment() {

    //instanciar los fragmentos
    private val customerReviewsFragment = CustomerReviewsFragment()
    private val customerScheduleFragment = CustomerScheduleFragment()
    private val customerChatFragment = ChatFragment()
    var getNombre: String? = ""
    var getDireccion: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_customer_store, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // super.onCreate(savedInstanceState)
        getNombre = arguments?.getString("nombre")
        getDireccion = arguments?.getString("direccion")
        val Nombre = view?.findViewById<TextView>(R.id.lblNegocioNombreCliente)
        val Direccion = view?.findViewById<TextView>(R.id.lblDireccionRealCliente)
        if (Nombre != null && Direccion!=null) {
            Nombre.setText(getNombre);
            Direccion.setText(getDireccion);
        };

        //ir al fragmento de reseñas
        this.btnVerResñasCliente.setOnClickListener {
            nextFragment(customerReviewsFragment)
        }

        //ir al fragmento de agendar cita
        this.btnAgendarCliente.setOnClickListener {
            nextFragment(customerScheduleFragment)
        }

        //ir al fragmento del chat
        this.btnEnviarMensajeCliente.setOnClickListener {
            nextFragment(customerChatFragment)
        }


    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CustomerStoreFragment().apply {
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


}