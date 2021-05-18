package com.example.meetandfix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meetandfix.R
import com.example.meetandfix.ReviewAdapter.ReviewAdapter
import com.example.meetandfix.ReviewAdapter.ReviewModel
import kotlinx.android.synthetic.main.fragment_customer_reviews.*

class RepairerReviewsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repairer_reviews, container, false)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // super.onCreate(savedInstanceState)
        val recycler= view?.findViewById<RecyclerView>(R.id.Repairer_ReviesRecyclerID)
        recycler?.adapter = ReviewAdapter(listOf(
            ReviewModel(0,"sadas","reseña","10/10/10"),
            ReviewModel(0,"sadas","reseña","10/10/10"),
            ReviewModel(0,"sadas","reseña","10/10/10")
        ))

    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            RepairerReviewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}