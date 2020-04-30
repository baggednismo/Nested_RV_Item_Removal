package com.devinmartinolich.nestedrvitemremoval.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.devinmartinolich.nestedrvitemremoval.views.MainFragment
import com.devinmartinolich.nestedrvitemremoval.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.flContainer,
                Fragment.instantiate(this, MainFragment::class.java.name))
            .commit()
    }
}