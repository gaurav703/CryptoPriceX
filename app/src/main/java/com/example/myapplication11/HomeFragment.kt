package com.example.myapplication11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication11.adapter.Topmarketadapter
import com.example.myapplication11.adapter.toplosssgainadapter
import com.example.myapplication11.apis.ApiUtilites
import com.example.myapplication11.apis.apiInterface
import com.example.myapplication11.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        getTopcurrency()
        setTablayout()
        return binding.root
    }
    private fun getTopcurrency() {
        lifecycleScope.launch(Dispatchers.IO) {
            val res = ApiUtilites.getInstance().create(apiInterface::class.java).getMarketData()
            withContext(Dispatchers.Main){
                binding.topCurrencyRecyclerView.adapter = Topmarketadapter(requireContext(),res.body()!!.data.cryptoCurrencyList)
            }
            Log.d("hii", "getTopcurrency:${res.body()!!.data.cryptoCurrencyList} ")
        }

    }

    private fun setTablayout(){
        val adapter =toplosssgainadapter(this)
        binding.contentViewPager.adapter=adapter
        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 0){
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topLoseIndicator.visibility= GONE
            }else{
                    binding.topGainIndicator.visibility = GONE
                    binding.topLoseIndicator.visibility= VISIBLE
                }

            }
        })
        TabLayoutMediator(binding.tabLayout, binding.contentViewPager){
            tab ,position ->
              var title = if(position == 0){
                  "Top Gainers"
              }else{
                  "Top Lossers"
              }
            tab.text = title
        }.attach()


        }
    }
