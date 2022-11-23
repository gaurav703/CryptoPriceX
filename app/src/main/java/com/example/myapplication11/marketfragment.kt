package com.example.myapplication11

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.ListView
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplication11.adapter.Marketadapter
import com.example.myapplication11.apis.ApiUtilites
import com.example.myapplication11.apis.apiInterface
import com.example.myapplication11.databinding.FragmentMarketfragmentBinding
import com.example.myapplication11.models.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class marketfragment : Fragment() {

    private lateinit var binding: FragmentMarketfragmentBinding
    private lateinit var list: List<CryptoCurrency>
    private lateinit var adapter: Marketadapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentMarketfragmentBinding.inflate(layoutInflater)
       list  = listOf()

        adapter =Marketadapter(requireContext(),list)

       binding.currencyRecyclerView.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO){
            val res = ApiUtilites.getInstance().create(apiInterface::class.java).getMarketData()
            if(res.body() != null){
                withContext(Dispatchers.Main){
                    list=res.body()!!.data.cryptoCurrencyList

                    adapter.updateData(list)
                }
            }
        }

        searchcoin()
        return binding.root
    }


    lateinit var SearchText : String
    private fun searchcoin(){
           binding.searchEditText.addTextChangedListener(object:TextWatcher{
               override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

               }
               override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

               }
               override fun afterTextChanged(p0: Editable?) {
                  SearchText = p0.toString().toLowerCase()
                   updateRecycleview()
               }
           })


    }

    private fun updateRecycleview(){
        val data = ArrayList<CryptoCurrency>()
        for(item in list){
            val coinName = item.name.lowercase(Locale.getDefault())
            val coinsymbol = item.symbol.lowercase(Locale.getDefault())

            if( coinName.contains(SearchText) || coinsymbol.contains(SearchText)){
                   data.add(item)
            }
        }
        adapter.updateData(data)
    }
}

