package com.example.roomtraining.screens

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomtraining.MainViewModel
import com.example.roomtraining.R

class CreateUserFragment : Fragment(R.layout.create_user_fragment) {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val firstNameEditText = view.findViewById<EditText>(R.id.editTextFirstName)
        val lastNameEditText = view.findViewById<EditText>(R.id.editTextLastName)
        val ageEditText = view.findViewById<EditText>(R.id.editTextAge)

        viewModel.getSuccessLiveData().observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createUserFragment_to_mainFragment)
        })

        viewModel.getErrorLiveData().observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        view.findViewById<Button>(R.id.postButton).setOnClickListener {
            viewModel.insertUser("${firstNameEditText.text} ${lastNameEditText.text}",
                ageEditText.text.toString())
        }
    }

}