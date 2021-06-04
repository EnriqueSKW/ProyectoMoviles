package com.example.meetandfix.ReviewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meetandfix.R

class ReviewAdapter(val reviews: List<ReviewModel>):RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val Nombre = view.findViewById<TextView>(R.id.Card_Review_NombreClienteID)
        private val Reseña = view.findViewById<TextView>(R.id.Card_Review_ReseñaClienteID)
  

        fun bind(review: ReviewModel) {
            Nombre.text=review.nombre
            Reseña.text=review.reseña

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.viewlook,parent,false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review=reviews[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}