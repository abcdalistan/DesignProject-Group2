package com.example.abiotic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.abiotic.R
import com.example.abiotic.database.RegisterDatabase
import com.example.abiotic.database.RegisterRepository
import com.example.abiotic.databinding.FragmentRegisterBinding

class Register : Fragment() {
   // private lateinit var viewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )
      /*  val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getDatabase(application).registerDAO

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        viewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.errorToast.observe(viewLifecycleOwner, Observer { error->
            if(error==true){
                Toast.makeText(requireContext(), "Please input details!", Toast.LENGTH_SHORT).show()
                viewModel.donetoast()
            }
        })

        viewModel.errorUsername.observe(viewLifecycleOwner, Observer { error ->
            if (error == true) {
                Toast.makeText(requireContext(), "User already exists!", Toast.LENGTH_SHORT)
                    .show()
                viewModel.userName()
            }
        })

        viewModel.correct.observe(viewLifecycleOwner, Observer { noError ->
            if (noError == true) {
                Toast.makeText(requireContext(), "Register complete!", Toast.LENGTH_SHORT).show()
                viewModel.register()
            }
        })


      //  binding.Login.setOnClickListener { view: View ->
       //     view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment22)

      //  }
*/
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Register"
        //...
    }

}

