package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.view.View
/**
 *
 *                      UJM * EMSE
 *
 *                  * Aleksei PASHININ *
 *
 *                     WMP Project
 *
 */

import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.model.WindowDto
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UpdateActivity : BasicActivity() {

    val superList = mutableListOf<WindowDto?>()
    val superListStatus = mutableListOf<WindowDto?>()
    var globalId = 0

    var globalRoom1 = 0
    var globalRoom2 = 0
    var globalRoom3 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        status_msg.visibility = View.INVISIBLE;
        for (i in 1..9) {
            chargeWindows(i.toLong())
        }

        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        if (id != 0L) {
            lifecycleScope.launch(context = Dispatchers.IO) { // (1)
                runCatching { ApiServices().windowsApiService.findById(id).execute() } // (2)
                    .onSuccess {
                        withContext(context = Dispatchers.Main) { // (3)
                            status_msg.visibility = View.VISIBLE;
                            superList.add(it.body())
                            nextStep(id)
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

        val goToMenuUpdateResult = findViewById<Button>(R.id.sendUpdate)
        goToMenuUpdateResult.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra(WINDOW_NAME_PARAM, globalId.toLong())
            }
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

        val returnButton = findViewById(R.id.buttonReturn) as Button

        returnButton.setOnClickListener {
            finish()
        }


    }

    fun nextStep(id: Long) {
        val bodyDto = superList.get(0)
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().windowsApiService.switchStatus(id).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Update Successful",
                            Toast.LENGTH_LONG
                        ).show()
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
    }

    fun chargeWindows(idx: Long) {
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().windowsApiService.findById(idx).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        superListStatus.add(it.body())
                        chargeStatus(idx);
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

    }

    fun chargeStatus(id: Long) {
        println("X1-> " + superListStatus.size)
        val window = superListStatus.firstOrNull { it!!.id == id }
        println("XWINDOW ->" + window)
        if (window != null) {
            val open = "OPEN"
            if (id == 1L || id == 4L || id == 7L) {
                if (open.equals(window.windowStatus.toString(), true)) {
                    status1.text = "OPEN"
                    switch1.isChecked = true;
                } else {
                    status1.text = "CLOSED"
                    switch1.isChecked = false;
                }
            }
            if (id == 2L || id == 5L || id == 8L) {
                if (open.equals(window.windowStatus.toString(), true)) {
                    status2.text = "OPEN"
                    switch2.isChecked = true;
                } else {
                    status2.text = "CLOSED"
                    switch2.isChecked = false;
                }
            }
            if (id == 3L || id == 6L || id == 9L) {
                if (open.equals(window.windowStatus.toString(), true)) {
                    status3.text = "OPEN"
                    switch3.isChecked = true;
                } else {
                    status3.text = "CLOSED"
                    switch3.isChecked = false;
                }
            }
        } else {
            System.out.println("NULL")
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view == switch1) {
            if (globalRoom1 == 1 && switch1.isChecked) {
                status1.text = "OPEN"
                globalId = 1
            } else if (globalRoom1 == 1) {
                status1.text = "CLOSED"
                globalId = 1
            } else if (globalRoom2 == 1 && switch1.isChecked) {
                status1.text = "OPEN"
                globalId = 4
            } else if (globalRoom2 == 1) {
                status1.text = "CLOSED"
                globalId = 4
            } else if (globalRoom3 == 1 && switch1.isChecked) {
                status1.text = "OPEN"
                globalId = 7
            } else if (globalRoom3 == 1) {
                status1.text = "CLOSED"
                globalId = 7
            } else {
                globalId = 0
            }
        } else if (view == switch2) {
            if (globalRoom1 == 1 && switch2.isChecked) {
                status2.text = "OPEN"
                globalId = 2
            } else if (globalRoom1 == 1) {
                status2.text = "CLOSED"
                globalId = 2
            } else if (globalRoom2 == 1 && switch2.isChecked) {
                status2.text = "OPEN"
                globalId = 5
            } else if (globalRoom2 == 1) {
                status2.text = "CLOSED"
                globalId = 5
            } else if (globalRoom3 == 1 && switch2.isChecked) {
                status2.text = "OPEN"
                globalId = 8
            } else if (globalRoom3 == 1) {
                status2.text = "CLOSED"
                globalId = 8
            } else {
                globalId = 0
            }
        } else if (view == switch3) {
            if (globalRoom1 == 1 && switch3.isChecked) {
                status3.text = "OPEN"
                globalId = 3
            } else if (globalRoom1 == 1) {
                status3.text = "CLOSED"
                globalId = 3
            } else if (globalRoom2 == 1 && switch3.isChecked) {
                status3.text = "OPEN"
                globalId = 6
            } else if (globalRoom2 == 1) {
                status3.text = "CLOSED"
                globalId = 6
            } else if (globalRoom3 == 1 && switch3.isChecked) {
                status3.text = "OPEN"
                globalId = 9
            } else if (globalRoom3 == 1) {
                status3.text = "CLOSED"
                globalId = 9
            } else {
                globalId = 0
            }
        }
        println("GLOBAL: " + globalId)
    }


    fun onClick(v: View) {
        if (v === buttonRoom1) {
            for (i in 1..3) {
                chargeWindows(i.toLong())
            }
            buttonRoom1.background = resources.getDrawable(R.drawable.room_light);
            buttonRoom2.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom3.background = resources.getDrawable(R.drawable.room_dark);
            textViewRoom1.setTextColor(getResources().getColor(R.color.black));
            textViewRoom2.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom3.setTextColor(getResources().getColor(R.color.gray));
            globalRoom1 = 1
            globalRoom2 = 0
            globalRoom3 = 0
        } else if (v === buttonRoom2) {
            for (i in 4..6) {
                chargeWindows(i.toLong())
            }
            buttonRoom1.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom2.background = resources.getDrawable(R.drawable.room_light);
            buttonRoom3.background = resources.getDrawable(R.drawable.room_dark);
            textViewRoom1.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom2.setTextColor(getResources().getColor(R.color.black));
            textViewRoom3.setTextColor(getResources().getColor(R.color.gray));
            globalRoom1 = 0
            globalRoom2 = 1
            globalRoom3 = 0
        } else if (v === buttonRoom3) {
            for (i in 7..9) {
                chargeWindows(i.toLong())
            }
            buttonRoom1.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom2.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom3.background = resources.getDrawable(R.drawable.room_light);
            textViewRoom1.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom2.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom3.setTextColor(getResources().getColor(R.color.black));
            globalRoom1 = 0
            globalRoom2 = 0
            globalRoom3 = 1
        } else {
            buttonRoom1.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom2.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom3.background = resources.getDrawable(R.drawable.room_dark);
            textViewRoom1.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom2.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom3.setTextColor(getResources().getColor(R.color.gray));
            globalRoom1 = 0
            globalRoom2 = 0
            globalRoom3 = 1
        }
    }
}