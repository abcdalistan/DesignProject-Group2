package com.example.abiotic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.abiotic.databinding.FragmentHomeBinding


class Home : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container,false)
        binding.projectBtn.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_homePage_to_projectInformation)

        }

        binding.monitorBtn.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_homePage_to_monitor)

        }

        binding.dataBtn.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_homePage_to_data)

        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Home"

    }


}