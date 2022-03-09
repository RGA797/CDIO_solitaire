package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.Model.Columns
import org.json.JSONObject.NULL

class SolitaireSolver {
    val columns = Columns()

    fun solve(): String{

        //kan vi bruge rule 1


        //kan vi bruge rule 2

        //

        //



        return "solution"
    }


    fun ruleTwo(){

    }

    //returns the card with most backturned cards behind it





    fun getColumnWithAceOrTwo(): Int? {
        for (i in 0..6) {
            for (j in columns.bottomList[i]) {
                if (j.isDowncard) {
                    continue
                } else if (j.rank == 1)
                    return i
                else if (j.rank == 2)
                    return i
            }
        }
        return null
    }

    fun isValidMove(i: Int, cardToMove: Card, cardToMoveTo: Card): Boolean {

        var colorMoveValid: Boolean = false
        var rankMoveValid: Boolean = false

        if (cardToMove.suit == "S" || cardToMove.suit == "C" && cardToMoveTo.suit == "D" || cardToMoveTo.suit == "H") {//black card
            colorMoveValid = true
        } else if (cardToMove.suit == "D" || cardToMove.suit == "H" && cardToMoveTo.suit == "S" || cardToMoveTo.suit == "C") { //red card
            colorMoveValid = true
        }

        if (cardToMove.rank + 1 == cardToMoveTo.rank) { //Rank move valid
            rankMoveValid = true
        }

        return colorMoveValid && rankMoveValid
    }

    fun isValidMoveTopColumn(i: Int, cardToMove: Card, cardToMoveTo: Card): Boolean {
        var suitMoveValid: Boolean = false
        var rankMoveValid: Boolean = false


        if (cardToMove.suit == cardToMoveTo.suit || (cardToMove.rank == 1 && cardToMoveTo.suit == NULL)) {
            suitMoveValid = true
        }

        if (cardToMove.rank - 1 == cardToMoveTo.rank) { //Rank move valid
            rankMoveValid = true
        }

        if (cardToMove.rank == 1 && cardToMoveTo.rank == NULL) {
            rankMoveValid = true
        }

        return suitMoveValid && rankMoveValid

    }


}