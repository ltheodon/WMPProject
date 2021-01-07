package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class ProfileActivity : BasicActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val goToMenuHome = findViewById(R.id.buttonHome2) as Button
        goToMenuHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val goToMenuUpdate = findViewById(R.id.buttonUpdate2) as Button
        goToMenuUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        val goToMenuProfile = findViewById(R.id.buttonProfile2) as Button
        goToMenuProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val goToMenuGeneral = findViewById<Button>(R.id.buttonGenMenu)
        goToMenuGeneral.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val returnButton = findViewById(R.id.buttonReturn) as Button

        returnButton.setOnClickListener {
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}