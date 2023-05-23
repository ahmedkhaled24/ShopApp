package com.hamtary.shopapp.ui.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.hamtary.shopapp.R
import com.hamtary.shopapp.databinding.FragmentSplashBinding
import com.hamtary.shopapp.ui.Auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding
    private var isFirstTimeLaunchVar: Boolean? = false
    private var isLogin: Boolean? = false
    private val authViewModel: AuthViewModel by viewModels()
    private val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding!!.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            authViewModel.getUserInfo.observe(requireActivity()) { userInfo ->
                if (!(userInfo.token.isNullOrEmpty()))
                    isLogin = true
            }
        }
        binding.apply {
            authViewModel.isFirstTimeLaunch.observe(requireActivity()) { isFirstTimeLaunch ->
                Timber.tag("token").d(isFirstTimeLaunch.toString())
                isFirstTimeLaunchVar = when (isFirstTimeLaunch) {
                    true -> {
                        true

                    }

                    false -> {
                        false
                    }
                }
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            Handler(Looper.myLooper()!!).postDelayed(

                {
                    if (isFirstTimeLaunchVar == true && isLogin == true) {
                        lifecycleScope.launchWhenResumed {
                            findNavController().navigate(R.id.action_splashFragment_to_homeFragment,null,navOptions)
                        }


                    } else if (isFirstTimeLaunchVar == true && isLogin == false) {
                        lifecycleScope.launchWhenResumed {
                            findNavController().navigate(R.id.action_splashFragment_to_registerFragment,null,navOptions)
                        }
                    } else {
                        lifecycleScope.launchWhenResumed {
                            findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment,null,navOptions)
                        }
                    }

                }, 2000
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}