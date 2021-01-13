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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.model.ApiServices
import com.faircorp.model.OnWindowSelectedListener
import com.faircorp.model.RoomDto
import com.faircorp.model.WindowsAdapterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WindowsActivity : BasicActivity(), OnWindowSelectedListener {

    val superList = mutableListOf<RoomDto?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)

        val recyclerView = findViewById<RecyclerView>(R.id.list_windows) // (2)
        val adapter = WindowsAdapterView.WindowAdapter(this) // (3)
        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().roomsApiService.findById(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        superList.clear()
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

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().windowsApiService.findByIdOfRoom(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        adapter.update(it.body() ?: emptyList())
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

        val goToMenuHome = findViewById<Button>(R.id.buttonHome2)
        goToMenuHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val goToMenuUpdate = findViewById<Button>(R.id.buttonUpdate2)
        goToMenuUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

        val goToMenuProfile = findViewById<Button>(R.id.buttonProfile2)
        goToMenuProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val goToMenuGeneral = findViewById<Button>(R.id.buttonGenMenu)
        goToMenuGeneral.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val returnButton = findViewById<Button>(R.id.buttonReturn)

        returnButton.setOnClickListener {
            finish()
        }
    }

    override fun onWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).apply {
            putExtra(WINDOW_NAME_PARAM, id)
        }
        val ids = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        startActivity(intent)

    }

    fun showValues(id: Long) {
        val room = superList.firstOrNull { it!!.id == id }
        println("WINDOW ->" + room)
        if (room != null) {
            println("ROOM:" + room.name + " CT:" + room.currentTemperature?.toString() + " TT:" + room.targetTemperature?.toString())
            findViewById<TextView>(R.id.txt_room_number).text = room.name;
            findViewById<TextView>(R.id.cr_temp).text = room.currentTemperature?.toString()
            findViewById<TextView>(R.id.tr_temp).text = room.targetTemperature?.toString()
        } else {
            System.out.println("NULL")
        }
    }
}