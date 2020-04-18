package com.yellowlego.parayo.intro

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yellowlego.parayo.R
import com.yellowlego.parayo.api.ParayoApi
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class IntroFragment : Fragment() {

    companion object {
        fun newInstance() = IntroFragment()
    }

    private lateinit var viewModel: IntroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        runBlocking {
            try {
                val response = ParayoApi.instance.hello()
                Log.d("IntroFragment", response.data)
            } catch (e: Exception) {
                Log.e("IntroFragment", "Hello API 호출 오류", e)
            }
        }
        return inflater.inflate(R.layout.intro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IntroViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
