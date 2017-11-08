package com.imchen.xposedtools

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testToast()
    }

    private fun testToast(){
        Toast.makeText(applicationContext,"hello kotlin",Toast.LENGTH_SHORT).show()
    }
}