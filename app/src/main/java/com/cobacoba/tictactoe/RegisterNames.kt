package com.cobacoba.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_names.*

class RegisterNames : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_names)
        btnBegin.setOnClickListener {
            var nameOne: String = playerOneName.text.toString()
            var nameTwo: String = playerTwoName.text.toString()
            if(nameOne.equals("")|| nameTwo.equals("")){
                Toast.makeText(this, "Name must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("PlayerOneName", nameOne)
                intent.putExtra("PlayerTwoName", nameTwo)
                startActivity(intent)
            }
        }
    }
}