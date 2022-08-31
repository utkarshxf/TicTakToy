package com.example.orion.tictacgame

import android.annotation.SuppressLint
import android.icu.text.BreakIterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.telephony.CellIdentity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.thread

import kotlin.random.Random as Random1



class MainActivity() : AppCompatActivity(), Parcelable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun buclick(view: View) {
        val bq = view as Button
        var cellid = 0
        when (bq.id) {
            R.id.a1b1 -> cellid = 1
            R.id.a1b2 -> cellid = 2
            R.id.a1b3 -> cellid = 3
            R.id.a2b1 -> cellid = 4
            R.id.a2b2 -> cellid = 5
            R.id.a2b3 -> cellid = 6
            R.id.a3b1 -> cellid = 7
            R.id.a3b2 -> cellid = 8
            R.id.a3b3 -> cellid = 9

        }
        bq.isEnabled = false
        playgame(cellid, bq)
    }

//            Log.d("buclick: ", bq.id.toString())
//        Log.d( "buclick: ", cellid.toString())
    var actply = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    constructor(parcel: Parcel) : this() {
        actply = parcel.readInt()
    }

    fun playgame(cellid: Int, bq: Button) {
        if (actply == 1) {
            bq.text = "X"
//            bq.setBackgroundResource(R.color.white)
            player1.add(cellid)
            actply = 2
            autoplay()



        } else {
            bq.text = "O"
//            bq.textColors(R.color.white)
//            bq.setBackgroundResource(R.color.yellow)
            player2.add(cellid)
            actply = 1

        }

//         bq.isEnabled = false
         checkwhowin()

//        Thread.sleep(1000)


    }

    fun checkwhowin(){
        var win = -1
        if (player1.contains(1)&&player1.contains(2)&&player1.contains(3)){
            win = 1
        }
        if (player2.contains(1)&&player2.contains(2)&&player2.contains(3)){
            win = 2
        }
        if (player1.contains(4)&&player1.contains(5)&&player1.contains(6)){
            win = 1
        }
        if (player2.contains(4)&&player2.contains(5)&&player2.contains(6)){
            win = 2
        }
        if (player1.contains(7)&&player1.contains(8)&&player1.contains(9)){
            win = 1
        }
        if (player2.contains(7)&&player2.contains(8)&&player2.contains(9)){
            win = 2
        }
        if (player1.contains(1)&&player1.contains(4)&&player1.contains(7)){
            win = 1
        }
        if (player2.contains(1)&&player2.contains(4)&&player2.contains(7)){
            win = 2
        }
        if (player1.contains(2)&&player1.contains(5)&&player1.contains(8)){
            win = 1
        }
        if (player2.contains(2)&&player2.contains(5)&&player2.contains(8)){
            win = 2
        }
        if (player1.contains(3)&&player1.contains(6)&&player1.contains(9)){
            win = 1
        }
        if (player2.contains(3)&&player2.contains(6)&&player2.contains(9)){
            win = 2
        }
        if (player1.contains(1)&&player1.contains(5)&&player1.contains(9)){
            win = 1
        }
        if (player2.contains(1)&&player2.contains(5)&&player2.contains(9)){
            win = 2
        }
        if (player1.contains(3)&&player1.contains(5)&&player1.contains(7)){
            win = 1
        }
        if (player2.contains(3)&&player2.contains(5)&&player2.contains(7)){
            win = 2
        }
        if (win==1){
            Toast.makeText(this, "player 1 is  WINNER", Toast.LENGTH_LONG).show()

            reset()

        }
        if (win==2) {
            Toast.makeText(this, "player 2 is  WINNER", Toast.LENGTH_LONG).show()

            reset()
        }



    }
    fun autoplay(){

            var empcell = ArrayList<Int>()
            for ( cellId in 1..9){
                if (!(player1.contains(cellId) || player2.contains(cellId))){
                    empcell.add(cellId)
                }

            }
            val r = Random()
            val rand = r.nextInt(empcell.size)
            val cellid = empcell[rand]

            var bq:Button ? = when(cellid) {
                1 -> a1b1
                2 -> a1b2
                3 -> a1b3
                4 -> a2b1
                5 -> a2b2
                6 -> a2b3
                7 -> a3b1
                8 -> a3b2
                else -> {
                    a3b3
                }
            }
            bq!!.isEnabled = false
            playgame(cellid, bq!! )


    }

    fun reset(){
        player1.clear()
        player2.clear()
        for(cellId in 1..9) {
            var bq: Button?= when (cellId) {
                1 -> a1b1
                2 -> a1b2
                3 -> a1b3
                4 -> a2b1
                5 -> a2b2
                6 -> a2b3
                7 -> a3b1
                8 -> a3b2
                else -> { a3b3 }
            }
            bq!!.text = ""
            bq.isEnabled = true
        }

    }




    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(actply)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

}