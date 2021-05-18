package com.example.meetandfix.NotificationAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meetandfix.R



class NotifAdapter(val notifications: List<NotifModel> ): RecyclerView.Adapter<NotifAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val Nombre = view.findViewById<TextView>(R.id.lblNotificationTitleCliente)
        private val Descripcion= view.findViewById<TextView>(R.id.lblNotificationDescriptionCliente)

        fun bind(notification: NotifModel) {
            Nombre.text=notification.nombre
            Descripcion.text=notification.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,parent,false)
        return NotifAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification=notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int {
       return notifications.size
    }

}