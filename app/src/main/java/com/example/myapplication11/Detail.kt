package com.example.myapplication11


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.myapplication11.databinding.ActivityDetailBinding
import com.example.myapplication11.models.CryptoCurrency

class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var list: List<CryptoCurrency>
    private var p: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        p= intent.getIntExtra("pos",0)
         val item = list[p!!]
         binding.detailPriceTextView.text = item.name
         binding.detailSymbolTextView.text= item.symbol

         Glide.with(this).load(
            "https://s2.coinmarketcap.com/static/img/coins/64x64/"+ item.id + ".png"
         ).into(binding.detailImageView)

        if(item.quotes[0].percentChange24h > 0 ){
            binding.detailChangeTextView.setTextColor(this.resources.getColor(R.color.green))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
            binding.detailChangeTextView.text = "$"+"${String.format("%.02f",item.quotes[0].price)}%"
        }
        else{
            binding.detailChangeTextView.setTextColor(this.resources.getColor(R.color.red))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
            binding.detailChangeTextView.text =  "$"+"${String.format("%.02f",item.quotes[0].price)}%"
        }

        if(item.quotes[0].percentChange24h > 0 ){
            binding.detailPriceTextView.setTextColor(this.resources.getColor(R.color.green))
            binding.detailPriceTextView.text =  "$"+"$${String.format("%.02f",item.quotes[0].price)}"
        }
        else{
            binding.detailPriceTextView.setTextColor(this.resources.getColor(R.color.red))
            binding.detailPriceTextView.text = "$"+"$${String.format("%.02f",item.quotes[0].price)}"
        }



    }
}
