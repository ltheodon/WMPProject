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
import android.net.Uri
import android.os.Bundle
import android.widget.Button


class MenuActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val goToLink1 = findViewById<Button>(R.id.menu_button1)
        goToLink1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val goToLink2 = findViewById<Button>(R.id.menu_button2)
        goToLink2.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        val goToLink3 = findViewById<Button>(R.id.menu_button3)
        goToLink3.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val goToLink4 = findViewById<Button>(R.id.menu_button4)
        goToLink4.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://dev-mind.fr"))
            )
        }

        val goToLink5 = findViewById<Button>(R.id.menu_button5)
        goToLink5.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto://guillaume@dev-mind.fr"))
            )
        }

        val returnButton = findViewById<Button>(R.id.buttonReturn)

        returnButton.setOnClickListener {
            finish()
        }

    }
}