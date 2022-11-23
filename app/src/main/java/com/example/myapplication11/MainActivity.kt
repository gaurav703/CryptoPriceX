package com.example.myapplication11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replacefragment(HomeFragment())
        binding.bottomNavigationView2.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homefragment -> replacefragment(HomeFragment())
                R.id.marketfragment -> replacefragment(marketfragment())
               // R.id.watchlistfragment -> replacefragment(watchlistFragment())
                else -> {

                }
            }
            true
        }

        // bottom navagation
//        <item
//        android:title="Watchlist"
//        android:icon="@drawable/ic_bookmark"
//        android:id="@+id/watchlistfragment"/>
    }

    private fun replacefragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_Layout, fragment)
        fragmentTransition.commit()
    }

}