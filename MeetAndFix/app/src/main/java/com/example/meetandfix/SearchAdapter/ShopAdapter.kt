package com.example.meetandfix.SearchAdapter

import android.graphics.BitmapFactory
import android.media.Image
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meetandfix.R
import java.util.*
import java.util.Base64.getDecoder

class ShopAdapter(val shops: List<ShopModel>, private var clickListener: ClickListener ): RecyclerView.Adapter<ShopAdapter.ViewHolder>() {



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {


        private val Nombre = view.findViewById<TextView>(R.id.NameRepairerShopID)
        private val Direccion= view.findViewById<TextView>(R.id.DireccionRepairerShopID)
        private val Imagen= view.findViewById<ImageView>(R.id.Image_RepairerShopID)



        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(shop: ShopModel) {
            Nombre.text=shop.nombre
            Direccion.text=shop.direccion
            if(shop.image != null)
            {
                val imgByteArrayFA =  getDecoder().decode(shop.image)
                val decodedImage = BitmapFactory.decodeByteArray(imgByteArrayFA, 0, imgByteArrayFA.size)
                Imagen.setImageBitmap(decodedImage)
            }

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