package com.example.mvisample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.mvisample.feature.LandingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    LandingFragment.newInstance(),
                    LandingFragment.TAG
                )
                .commitNow()
        }
    }

    fun showSpinner(isLoading: Boolean) {
        findViewById<LinearLayout>(R.id.loading_spinner).visibility =
            if(isLoading) View.VISIBLE else View.GONE
    }
}