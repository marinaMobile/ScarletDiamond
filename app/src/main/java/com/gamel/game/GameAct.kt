package com.gamel.game

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gamel.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*


class GameAct : AppCompatActivity() {
    var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        randPos(diamond_one, 300)
        randPos(diamond_two, 300)
        randPos(diamond_three, 300)
        randPos(diamond_four, 300)
        randPos(diamond_five, 300)
        randPos(diamond_six, 300)
        randPos(diamond_seven, 300)
        randPos(diamond_eight, 300)
        randPos(diamond_nine, 300)
        randPos(diamond_ten, 300)
        randPos(diamond_eleven, 300)
        randPos(diamond_twelve, 300)
        randPos(diamond_thirteen, 300)

        diamond_one.setOnClickListener{
            diamond_one.visibility = View.INVISIBLE
        }
        diamond_two.setOnClickListener{
            diamond_two.visibility = View.INVISIBLE
        }
        diamond_three.setOnClickListener{
            diamond_three.visibility = View.INVISIBLE
        }
        diamond_four.setOnClickListener{
            diamond_four.visibility = View.INVISIBLE
        }
        diamond_five.setOnClickListener{
            diamond_five.visibility = View.INVISIBLE
        }
        diamond_six.setOnClickListener{

            diamond_six.visibility = View.INVISIBLE
        }
        diamond_seven.setOnClickListener{

            diamond_seven.visibility = View.INVISIBLE
        }
        diamond_eight.setOnClickListener{

            diamond_eight.visibility = View.INVISIBLE
        }
        diamond_nine.setOnClickListener{
            diamond_nine.visibility = View.INVISIBLE
        }
        diamond_ten.setOnClickListener{
            diamond_ten.visibility = View.INVISIBLE
        }
        diamond_eleven.setOnClickListener{
            diamond_eleven.visibility = View.INVISIBLE
        }
        diamond_twelve.setOnClickListener{
            diamond_twelve.visibility = View.INVISIBLE
        }
        diamond_thirteen.setOnClickListener{
            diamond_thirteen.visibility = View.INVISIBLE
        }


        val running : TextView = findViewById(R.id.running)
        val timeRan : TextView = findViewById(R.id.timeRan)


        val s : Long = "10".toLong() * 1000

        object : CountDownTimer( s , 1000) {

            override fun onTick(millisUntilFinished: Long) {
                running.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                timeRan.text = "Time's Up!"
                MaterialAlertDialogBuilder(this@GameAct)
                    .setTitle("Time's Up")
                    .setCancelable(false)
                    .setPositiveButton("Play again"){dialog, _ ->
                        val preferences = getSharedPreferences("PREF", 0)
                        val editor = preferences.edit()
                        editor.apply()
                       startActivity(Intent(applicationContext, GameAct::class.java))
                        finish()
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }.start()
    }




    private fun randPos(Btn: ImageView, Time:Long) {
        val time: Long = Time
        val btn: ImageView = Btn
        val screenWidth = this.resources.displayMetrics.widthPixels
        val screenHeight = this.resources.displayMetrics.heightPixels
        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val rand = Random()
                    val dx: Float = rand.nextFloat() * (screenWidth-diamond_one.width)
                    val dy: Float = rand.nextFloat() * (screenHeight-diamond_one.height)
                    btn.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start()
                }
            }
        }, 0, time)
    }
}