package com.example.myapplication11.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication11.R
import com.example.myapplication11.databinding.TopCurrencyLayoutBinding
import com.example.myapplication11.models.CryptoCurrency

class Topmarketadapter(var context : Context, var list : List<CryptoCurrency>) : RecyclerView.Adapter<Topmarketadapter.Topmarketviewholder>() {

    inner class Topmarketviewholder(view : View) : RecyclerView.ViewHolder(view){
           var binding = TopCurrencyLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Topmarketviewholder {
       return Topmarketviewholder(LayoutInflater.from(context).inflate(R.layout.top_currency_layout, parent,false))
    }

    override fun onBindViewHolder(holder: Topmarketviewholder, position: Int) {
       val item = list[position]
        holder.binding.topCurrencyNameTextView.text = item.name

        Glide.with(context).load(
            "https://s2.coinmarketcap.com/static/img/coins/64x64/"+ item.id + ".png"
        ).into(holder.binding.topCurrencyImageView)

        if(item.quotes!![0].percentChange24h > 0 ){
           holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.topCurrencyChangeTextView.text = "$${String.format("%.02f",item.quotes[0].price)}"
        }
        else{
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.topCurrencyChangeTextView.text = "$${String.format("%.02f",item.quotes[0].price)}"
        }
    }
    override fun getItemCount(): Int {
       return list.size
    }

}

