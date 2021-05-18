package com.example.meetandfix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meetandfix.NotificationAdapter.NotifAdapter
import com.example.meetandfix.NotificationAdapter.NotifModel
import com.example.meetandfix.R


class RepairerNotificationsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repairer_notifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //super.onCreate(savedInstanceState)
        val recycler= view?.findViewById<RecyclerView>(R.id.Repairer_NotificationsRecyclerID)
        recycler?.adapter = NotifAdapter(listOf(
            NotifModel(0,"asddas","NotificacionDesc"),
            NotifModel(0,"asddas","NotificacionDesc"),
            NotifModel(0,"asddas","NotificacionDesc"),
            NotifModel(0,"asddas","NotificacionDesc")
        ))
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            RepairerNotificationsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}