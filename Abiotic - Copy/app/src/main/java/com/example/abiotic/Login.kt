package com.example.abiotic

import android.os.Bundle
import android.util.Log
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
import com.example.abiotic.databinding.FragmentLoginBinding


class Login : Fragment() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dao = RegisterDatabase.getDatabase(application).registerDAO
        val repository = RegisterRepository(dao)
        val factory = LoginViewModelFactory(repository, application)

        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.myLoginViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.errorToast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please input details!", Toast.LENGTH_SHORT).show()
                viewModel.donetoast()
            }
        })

        viewModel.errorUsername .observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "User doesn't exist!", Toast.LENGTH_SHORT).show()
                viewModel.errorUsername()
            }
        })

        viewModel.errorInvalidPassword.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please check your password!", Toast.LENGTH_SHORT).show()
                viewModel.invalidPassword()
            }
        })

        viewModel.correct .observe(
            viewLifecycleOwner,
            Observer { hasnoError ->
                if (hasnoError == true) {
                    Toast.makeText(requireContext(),
                        "Welcome!",
                        Toast.LENGTH_SHORT).show()
                    //displayHome()
                    viewModel.home()
                    //view?.findNavController()?.navigate(R.id.action_loginFragment2_to_homeFragment)
                }
            }
        )

       // binding.registerBtn.setOnClickListener { view: View ->
      //      view.findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
       //     Toast.makeText(view.context, "Register! ", Toast.LENGTH_SHORT).show()
      //  }

        return binding.root
    }
   // private fun displayHome() {
    //    Log.i("MYTAG","Home")
        // val action = LoginFragment2Directions.actionLoginFragment2ToHomeFragment()
        // NavHostFragment.findNavController(this).navigate(action)
    //    view?.findNavController()?.navigate(R.id.action_loginFragment2_to_homeFragment)

  //  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Login"

    }
}
