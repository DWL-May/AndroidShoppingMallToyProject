package com.yellowlego.parayo.signup

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.yellowlego.parayo.R
import com.yellowlego.parayo.databinding.SignupFragmentBinding

class SignupFragment : Fragment() {

    lateinit var binding: SignupFragmentBinding
    private lateinit var viewModel: SignupViewModel

    companion object {
        fun newInstance() = SignupFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.signup_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)
        binding.viewModel = viewModel
    }

}
