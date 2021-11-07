package com.example.roomtraining.screens

import android.os.Bundle
import android.view.View
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtraining.MainViewModel
import com.example.roomtraining.R
import com.example.roomtraining.screens.adapters.UsersRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val recycleView = view.findViewById<RecyclerView>(R.id.usersRecycleView)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        val adapter = UsersRecyclerAdapter()
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getReadAllData().observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createUserFragment)
        }
    }

}