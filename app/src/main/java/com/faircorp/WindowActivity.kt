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
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.model.OnWindowSelectedListener
import com.faircorp.model.WindowDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"


class WindowActivity : BasicActivity(), OnWindowSelectedListener {

    val superList = mutableListOf<WindowDto?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().windowsApiService.findById(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        superList.add(it.body())
                        showValues(id);
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        val goToMenuHome = findViewById(R.id.buttonHome3) as Button
        goToMenuHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val goToMenuUpdate = findViewById(R.id.buttonUpdate3) as Button
        goToMenuUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        val goToMenuProfile = findViewById(R.id.buttonProfile3) as Button
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

    fun showValues(id: Long) {
        println("1-> " + superList.size)
        val window = superList.firstOrNull { it!!.id == id }
        println("WINDOW ->" + window)
        if (window != null) {
            findViewById<TextView>(R.id.txt_window_name).text = window.name
            findViewById<TextView>(R.id.txt_room_name).text = window.roomDto.name
            findViewById<TextView>(R.id.txt_window_current_temperature).text =
                window.roomDto.currentTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_target_temperature).text =
                window.roomDto.targetTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_status).text = window.windowStatus.toString()
        } else {
            System.out.println("NULL")
        }
    }

    override fun onWindowSelected(id: Long) {
        TODO("Not yet implemented")
    }
}