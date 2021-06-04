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
import com.example.meetandfix.UserModel.UserModel
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
        val List:MutableList<ReviewModel> = ArrayList()
        List.add(ReviewModel(0,"sadas","reseña","1","1"))
        List.add(ReviewModel(0,"sadas","reseña2","1","1"))
        List.add(ReviewModel(0,"sadas","reseña3","1","1"))

        // super.onCreate(savedInstanceState)
        val recycler= view?.findViewById<RecyclerView>(R.id.Repairer_ReviesRecyclerID)
        recycler?.adapter = ReviewAdapter(List)

    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            RepairerReviewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}