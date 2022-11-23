package com.example.myapplication11.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication11.R
import com.example.myapplication11.databinding.CurrencyItemLayoutBinding
import com.example.myapplication11.Detail
import com.example.myapplication11.models.CryptoCurrency

class Marketadapter(var context : Context, var list : List<CryptoCurrency>) : RecyclerView.Adapter<Marketadapter.MarketViewHolder>(){

    inner class MarketViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var binding = CurrencyItemLayoutBinding.bind(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent,false))
    }

    fun updateData(dataitem : List<CryptoCurrency>){
        list = dataitem
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item = list[position]
        holder.binding.currencyNameTextView.text= item.name
        holder.binding.currencySymbolTextView.text=item.symbol
        Glide.with(context).load(
            "https://s2.coinmarketcap.com/static/img/coins/64x64/"+ item.id + ".png"
        ).into(holder.binding.currencyImageView)
        holder.binding.share.setOnClickListener{
            val intent = Intent()
            val message = item.name + "\n" + "price:"+ "$"+ String.format("%.02f",item.quotes[0].price)

            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type = "text/plain"
            startActivity(context,Intent.createChooser(intent,"Share to ...."),Bundle() )
        }


        if(item.quotes!![0].percentChange24h > 0 ){
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.currencyChangeTextView.text = "${String.format("%.02f",item.quotes[0].percentChange24h)}%"
        }
        else{
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.currencyChangeTextView.text = "${String.format("%.02f",item.quotes[0].percentChange24h)}%"
        }

        if(item.quotes!![0].percentChange24h > 0 ){
            holder.binding.currencyPriceTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.currencyPriceTextView.text = "$${String.format("%.02f",item.quotes[0].price)}"
        }
        else{
            holder.binding.currencyPriceTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.currencyPriceTextView.text ="$${String.format("%.02f",item.quotes[0].price)}"
        }

//        holder.itemView.setOnClickListener{
//            val intent = Intent(context,Detail::class.java)
//            intent.putExtra("pos",position)
//            startActivity(context,intent,Bundle())
//        }
    }



    override fun getItemCount(): Int {
   return list.size
    }


}



