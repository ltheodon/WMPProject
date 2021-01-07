package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.model.WindowDto
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UpdateActivity : BasicActivity(){

    val superList = mutableListOf<WindowDto?>()
    var globalId = 0

    var globalRoom1 = 0
    var globalRoom2 = 0
    var globalRoom3 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        status_msg.visibility = View.INVISIBLE;

        println("GENERAL START")
        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        println("ID POTENTIAL: $id")
        if (id != 0L) {
            println("GO GO GO")
            lifecycleScope.launch(context = Dispatchers.IO) { // (1)
                println("GO GO GO2")
                runCatching { ApiServices().windowsApiService.findById(id).execute() } // (2)
                        .onSuccess {
                            println("GO GO GO3")
                            withContext(context = Dispatchers.Main) { // (3)
                                println("01-> " + superList.size)
                                superList.add(it.body())
                                println("02-> " + superList.size)
                                println("RES: " + it.body())
                            }
                        }
                        .onFailure {
                            withContext(context = Dispatchers.Main) { // (3)
                                Toast.makeText(
                                        applicationContext,
                                        "Error on windows loading $it",
                                        Toast.LENGTH_LONG
                                ).show()
                                nextStep(id)
                            }
                        }
            }

            status_msg.visibility = View.VISIBLE;
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

    fun nextStep(id:Long){
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

    fun onCheckboxClicked(view :View){
        println("THEREEEEEEEEEEEEEEE")
            if (view == switch1) {
                if(globalRoom1==1 && switch1.isChecked){
                    println("THERE1")
                    status1.text = "OPEN"
                    globalId = 1
                }
                else{
                    println("THERE2")
                    status1.text = "CLOSED"
                    globalId = 0
                }
            }
            /*switch2.isChecked && buttonRoom1.isPressed -> status2.text = "OPEN"
            switch2.isChecked && buttonRoom1.isPressed -> globalId = 2
            !switch2.isChecked -> status2.text = "CLOSED"
            !switch2.isChecked -> globalId = 0
            switch3.isChecked && buttonRoom1.isPressed -> status3.text = "OPEN"
            switch3.isChecked && buttonRoom1.isPressed -> globalId = 7
            !switch3.isChecked -> status3.text = "CLOSED"
            !switch3.isChecked -> globalId = 0
            switch1.isChecked && buttonRoom2.isPressed -> status1.text = "OPEN"
            switch1.isChecked && buttonRoom2.isPressed -> globalId = 3
            !switch1.isChecked -> status1.text = "CLOSED"
            !switch1.isChecked -> globalId = 0
            switch2.isChecked && buttonRoom2.isPressed -> status2.text = "OPEN"
            switch2.isChecked && buttonRoom2.isPressed -> globalId = 4
            !switch2.isChecked -> status2.text = "CLOSED"
            !switch2.isChecked -> globalId = 0
            switch3.isChecked && buttonRoom2.isPressed -> status3.text = "OPEN"
            switch3.isChecked && buttonRoom2.isPressed -> globalId = 8
            !switch3.isChecked -> status3.text = "CLOSED"
            switch1.isChecked && buttonRoom3.isPressed -> status1.text = "OPEN"
            switch1.isChecked && buttonRoom3.isPressed -> globalId = 5
            !switch1.isChecked -> status1.text = "CLOSED"
            !switch1.isChecked -> globalId = 0
            switch2.isChecked && buttonRoom3.isPressed -> status2.text = "OPEN"
            switch2.isChecked && buttonRoom3.isPressed -> globalId = 6
            !switch2.isChecked -> status2.text = "CLOSED"
            !switch2.isChecked -> globalId = 0
            switch3.isChecked && buttonRoom3.isPressed -> status3.text = "OPEN"
            switch3.isChecked && buttonRoom3.isPressed -> globalId = 9
            !switch3.isChecked -> status3.text = "CLOSED"*/
        println("GLOBAL: "+globalId)
    }


    fun onClick(v: View) {
        if (v === buttonRoom1) {
            buttonRoom1.background = resources.getDrawable(R.drawable.room_light);
            buttonRoom2.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom3.background = resources.getDrawable(R.drawable.room_dark);
            textViewRoom1.setTextColor(getResources().getColor(R.color.black));
            textViewRoom2.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom3.setTextColor(getResources().getColor(R.color.gray));
            globalRoom1 = 1
            globalRoom2 = 0
            globalRoom3 = 0
        }
        else if (v === buttonRoom2) {
            buttonRoom1.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom2.background = resources.getDrawable(R.drawable.room_light);
            buttonRoom3.background = resources.getDrawable(R.drawable.room_dark);
            textViewRoom1.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom2.setTextColor(getResources().getColor(R.color.black));
            textViewRoom3.setTextColor(getResources().getColor(R.color.gray));
            globalRoom1 = 0
            globalRoom2 = 1
            globalRoom3 = 0
        }
        else if (v === buttonRoom3) {
            buttonRoom1.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom2.background = resources.getDrawable(R.drawable.room_dark);
            buttonRoom3.background = resources.getDrawable(R.drawable.room_light);
            textViewRoom1.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom2.setTextColor(getResources().getColor(R.color.gray));
            textViewRoom3.setTextColor(getResources().getColor(R.color.black));
            globalRoom1 = 0
            globalRoom2 = 0
            globalRoom3 = 1
        }
        else {
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