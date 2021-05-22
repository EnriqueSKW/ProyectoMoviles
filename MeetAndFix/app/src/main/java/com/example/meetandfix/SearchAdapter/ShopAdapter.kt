package com.example.meetandfix.SearchAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meetandfix.R

class ShopAdapter(val shops: List<ShopModel>, private var clickListener: ClickListener ): RecyclerView.Adapter<ShopAdapter.ViewHolder>() {



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {


        private val Nombre = view.findViewById<TextView>(R.id.NameRepairerShopID)
        private val Direccion= view.findViewById<TextView>(R.id.DireccionRepairerShopID)
        private val Imagen= view.findViewById<ImageView>(R.id.Image_RepairerShopID)

        fun bind(shop: ShopModel) {
            Nombre.text=shop.nombre
            Direccion.text=shop.direccion
            Glide.with(Imagen.context).load(shop.image).into(Imagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list,parent,false)
        return ShopAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shop=shops[position]
        holder.bind(shop)
        holder.itemView.setOnClickListener{
            clickListener.ClickedItem(shop)
        }
    }

    override fun getItemCount(): Int {
        return shops.size
    }

    interface ClickListener{
        fun ClickedItem(shop: ShopModel)
    }

}