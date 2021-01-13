/**
 *
 *                      UJM * EMSE
 *
 *                  * Aleksei PASHININ *
 *
 *                     WMP Project
 *
 */

package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToRoom1 = findViewById<Button>(R.id.button_room1)
        goToRoom1.setOnClickListener {
            val intent = Intent(this, WindowsActivity::class.java).apply {
                putExtra(WINDOW_NAME_PARAM, "1".toLong())
            }
            startActivity(intent)
        }

        val goToRoom2 = findViewById<Button>(R.id.button_room2)
        goToRoom2.setOnClickListener {
            val intent = Intent(this, WindowsActivity::class.java).apply {
                putExtra(WINDOW_NAME_PARAM, "2".toLong())
            }
            startActivity(intent)
        }

        val goToRoom3 = findViewById<Button>(R.id.button_room3)
        goToRoom3.setOnClickListener {
            val intent = Intent(this, WindowsActivity::class.java).apply {
                putExtra(WINDOW_NAME_PARAM, "3".toLong())
            }
            startActivity(intent)
        }

        val goToMenuHome = findViewById<Button>(R.id.buttonHome1)
        goToMenuHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val goToMenuUpdate = findViewById<Button>(R.id.buttonUpdate1)
        goToMenuUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        val goToMenuProfile = findViewById<Button>(R.id.buttonProfile1)
        goToMenuProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val goToMenuGeneral = findViewById<Button>(R.id.buttonGenMenu)
        goToMenuGeneral.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }
}