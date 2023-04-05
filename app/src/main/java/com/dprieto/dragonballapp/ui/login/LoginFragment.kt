package com.dprieto.dragonballapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.dprieto.dragonballapp.databinding.FragmentLoginBinding
import com.dprieto.dragonballapp.ui.detail.HeroDetailState
import com.dprieto.dragonballapp.ui.splash.SplashFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    companion object{
        var isUserLoggedInApp: Boolean = false
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            val credentials = viewModel.preLoadCredentials()

            editTextUser.setText(credentials.user)
            editTextPass.setText(credentials.pass)

            buttonLogin.setOnClickListener {
                viewModel.saveCredentials(editTextUser.text.toString(), editTextPass.text.toString())
                isUserLoggedInApp = false
                viewModel.doLogin()
            }

            viewModel.state.observe(viewLifecycleOwner){ state ->
                when(state){
                    is LoginState.Success -> {
                        viewModel.saveToken(state.token)
                        isUserLoggedInApp = true

                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHeroListFragment())
                    }
                    is LoginState.Error -> {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                    }
                    is LoginState.NetworkError -> {
                        Toast.makeText(requireContext(), "Network error with code ${state.code}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}