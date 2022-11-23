package com.example.myapplication11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication11.adapter.Marketadapter
import com.example.myapplication11.apis.ApiUtilites
import com.example.myapplication11.apis.apiInterface
import com.example.myapplication11.databinding.FragmentToplossGainBinding
import com.example.myapplication11.models.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList


class ToplossGainFragment : Fragment() {
   private lateinit var binding: FragmentToplossGainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentToplossGainBinding.inflate(layoutInflater)
        getMarketData()
        return return binding.root
    }
    private fun getMarketData(){
        val position = requireArguments().getInt("position")
        lifecycleScope.launch(Dispatchers.IO){
            val res = ApiUtilites.getInstance().create(apiInterface :: class.java).getMarketData()
            if( res.body() != null ){
                withContext(Dispatchers.Main){
                    val dataItem = res.body()!!.data.cryptoCurrencyList
                    Collections.sort(dataItem){
                       o1,o2 -> (o2.quotes[0].price.toInt())
                        .compareTo(o1.quotes[0].price.toInt())
                    }
                    val list = ArrayList<CryptoCurrency>()
                    if( position == 0){
                        list.clear()
                        for( i in 0..9){
                            list.add(dataItem[i])
                        }
                        binding.topGainLoseRecyclerView.adapter = Marketadapter(requireContext(),list)
                    }else{
                        list.clear()
                        for( i in 0..9){
                            list.add(dataItem[dataItem.size-i-1])
                        }
                        binding.topGainLoseRecyclerView.adapter = Marketadapter(requireContext(),list)
                    }
                }
            }
        }
    }
}

