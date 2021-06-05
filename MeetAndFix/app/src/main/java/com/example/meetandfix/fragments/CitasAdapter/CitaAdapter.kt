package com.example.meetandfix.fragments.CitasAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meetandfix.R

class CitaAdapter(val citas: List<CitaModel>): RecyclerView.Adapter<CitaAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val NombreCliente = view.findViewById<TextView>(R.id.Card_Nombre_CitaID)
        private val Fecha = view.findViewById<TextView>(R.id.Card_Fecha_CitaID)


        fun bind(cita: CitaModel) {
            NombreCliente.text=cita.nombreCliente
            Fecha.text=cita.fecha

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cita_agendada_item,parent,false);
        return CitaAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitaAdapter.ViewHolder, position: Int) {
        val cita=citas[position]
        holder.bind(cita)
    }

    override fun getItemCount(): Int {
        return citas.size
    }


}