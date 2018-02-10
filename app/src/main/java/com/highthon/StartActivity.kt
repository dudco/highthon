package com.highthon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_start.*
import com.facebook.FacebookSdk



class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(this.applicationContext)
        setContentView(R.layout.activity_start)

        pager.adapter = MyAdapter(supportFragmentManager)
    }

    inner class MyAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> Tuto1Fragment.create()
                1 -> Tuto2Fragment.create()
                2 -> Tuto3Fragment.create()
                else -> Tuto1Fragment.create()
            }
        }

        override fun getCount(): Int {
            return 3
        }
    }
}