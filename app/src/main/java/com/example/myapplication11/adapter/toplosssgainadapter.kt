package com.example.myapplication11.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication11.ToplossGainFragment

class toplosssgainadapter(fragment : Fragment):FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ToplossGainFragment()
        val bundle = Bundle()
        bundle.putInt("position",position)
        fragment.arguments= bundle
        return fragment
    }


}