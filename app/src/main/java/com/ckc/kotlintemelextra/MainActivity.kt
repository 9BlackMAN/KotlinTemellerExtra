package com.ckc.kotlintemelextra

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ckc.kotlintemelextra.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences : SharedPreferences


    var number =0
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        println("----------SharedPreferances----------")

        sharedPreferences =this.getSharedPreferences("com.ckc.kotlintemelextra",Context.MODE_PRIVATE)

        var a = sharedPreferences.getInt("key",0)

        if (a != 0){
            println(a)

        }else{
            println("Kaydedilen veri yok")
        }


    }
    fun save(view: View){

        sharedPreferences.edit().putInt("key",5).apply()

        var a = sharedPreferences.getInt("key",0)

        if (a != 0){
            println(a)

        }else{
            println("Kaydedilen veri yok")
        }
    }
    fun delete(view: View){
        sharedPreferences.edit().remove("key").apply()

        var a = sharedPreferences.getInt("key",0)

        if (a != 0){
            println(a)

        }else{
            println("Kaydedilen veri yok")
        }
    }
    fun intentUsing(view: View){

        val inttent = Intent(applicationContext,MainActivity2::class.java)
        inttent.putExtra("sifre",5)
        inttent.putExtra("sifre2","can")
        startActivity(inttent)

    }
    fun toast(view: View){
        Toast.makeText(this,"toast mesangeeee !!!",Toast.LENGTH_LONG).show()
    }
    fun alert(view: View){

        val alertt = AlertDialog.Builder(this@MainActivity)
        alertt.setTitle("UYARI")
        alertt.setMessage("Uygulama kapatılacak eminmşin ?")
        alertt.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(applicationContext,"yessssss",Toast.LENGTH_LONG).show()
        })
        alertt.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(applicationContext,"nooooooooooooo",Toast.LENGTH_LONG).show()
        })
        alertt.show()


    }
    fun can(view: View){

        object : CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {
                var a=(p0/1000).toString()
                binding.textView2.text = a
            }

            override fun onFinish() {
                println("Finishhhhh")
            }

        }.start()

    }
    fun handlerStart(view: View){


        number=0
        runnable = object : Runnable{
            override fun run() {

                number++
                binding.textView2.text = number.toString()

                handler.postDelayed(this,1000)

            }
        }
        handler.post(runnable)


    }
    fun handlerStop(view: View){

        handler.removeCallbacks(runnable)
        number=0
        binding.textView2.text = "0"

    }

}