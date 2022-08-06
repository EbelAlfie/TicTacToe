package com.cobacoba.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var turns: Boolean = false
    private var cells: MutableList<Button> = mutableListOf()
    private var playState: Array<Int> = arrayOf(0,0,0,0,0,0,0,0,0)
    private var playerOneScore = 0
    private var playerTwoScore = 0
    private var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        i = 1
        while (i <= 9) {
            var cellID: Int = resources.getIdentifier("cell" + i, "id", packageName)
            cells.add(findViewById(cellID))
            cells.get(i-1).setOnClickListener(this)
            i++
        }
        btn_reset.setOnClickListener {
            resetGame()
        }
    }

    private fun checkWinner(): Boolean {
        if((playState.get(0) != 0)&&(playState.get(1) != 0)&&(playState.get(2) != 0) && (playState.get(0)==playState.get(1))&&(playState.get(1)==playState.get(2))
            || (playState.get(3) != 0)&&(playState.get(4) != 0)&&(playState.get(5) != 0) && (playState.get(3) == playState.get(4))&&(playState.get(4) == playState.get(5))
            || (playState.get(6) != 0)&&(playState.get(7) != 0)&&(playState.get(8) != 0)&& (playState.get(6) == playState.get(7))&&(playState.get(7) == playState.get(8))){
            return true
        }
        if((playState.get(0) != 0)&&(playState.get(3) != 0)&&(playState.get(6) != 0)&& (playState.get(0) == playState.get(3))&&(playState.get(3) == playState.get(6))
            || (playState.get(1) != 0)&&(playState.get(4) != 0)&&(playState.get(7) != 0) &&  (playState.get(1) == playState.get(4))&&(playState.get(4) == playState.get(7))
            || (playState.get(2) != 0)&&(playState.get(5) != 0)&&(playState.get(8) != 0)&& (playState.get(2) == playState.get(5))&&(playState.get(5) == playState.get(8))){
            return true
        }
        if((playState.get(0) != 0)&&(playState.get(4) != 0)&&(playState.get(8) != 0) && (playState.get(0) == playState.get(4))&&(playState.get(4) == playState.get(8))
            || (playState.get(2) != 0)&&(playState.get(4) != 0)&&(playState.get(6) != 0)&& (playState.get(2) == playState.get(4))&&(playState.get(4) == playState.get(6))){
            return true
        }
        return false
    }

    private fun checkButtons(): Boolean {
        for(j in playState){
            if(j.equals(0)){
                return false
            }
        }
        return true
    }

    fun onPress(view: Button) {
        if (!view.text.toString().equals("")) {
            return
        }
        var s :String = view.toString()
        var i = Integer.parseInt(s.substring(s.length-2, s.length-1))
        if (!turns) {
            cells.get(i-1).text = "X"
            cells.get(i-1).setTextColor(resources.getColor(R.color.player_red))
            playState.set(i-1, 1)
        } else if (turns) {
            cells.get(i-1).text = "O"
            cells.get(i-1).setTextColor(resources.getColor(R.color.player_blue))
            playState.set(i-1, 2)
        }

            if (checkWinner()) {
                if (!turns) {
                    playerOneScore++
                    scoreone.text = playerOneScore.toString()
                    gameStats.text = "Player one wins"
                } else if(turns) {
                    playerTwoScore++
                    scoretwo.text = playerTwoScore.toString()
                    gameStats.text = "Player two wins"
                }
                TODO("stop the game")
            }else  if (checkButtons()) {
                gameStats.text = "Draw!"
            }
        turns = !turns
    }

    override fun onClick(view: View?) {
        if (view is Button) {
            onPress(view)
        }
    }

    fun resetGame(){
        var j = 0
        turns = false
        for(cell in cells){
            cell.text = ""
            playState[j] = 0
            j++
        }
    }
}