package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.Model.Rows

class solitaireSolver {
    val rows = Rows()


    fun getRowWithAceOrTwo(): Int?{
        for (i in 0..6) {
            for (j in rows.bottomList[i]) {
                if (j.isDowncard){
                    continue
                }
                else if (j.rank == 1)
                    return i
                else if (j.rank == 2)
                    return i
            }
        }
        return null
    }

    fun isValidMove(i: Int, cardToMove: Card, cardToMoveTo: Card): Boolean? {

        var colorMoveValid : Boolean = false
        var rankMoveValid : Boolean = false

        if (cardToMove.suit == "S" || cardToMove.suit == "C" && cardToMoveTo.suit == "D" || cardToMoveTo.suit == "H"){//black card
            colorMoveValid = true
        } else if (cardToMove.suit =="D" || cardToMove.suit =="H" && cardToMoveTo.suit == "S" || cardToMoveTo.suit == "C"){ //red card
            colorMoveValid = true
        }

        if (cardToMove.rank+1 == cardToMoveTo.rank){ //Rank move valid
            rankMoveValid = true
        }

        if (colorMoveValid && rankMoveValid){ //Valid move
            moveCard(i, cardToMove, cardToMoveTo)
            return true
        } else { //Move not valid
            return false
        }

    }


    fun moveCard(i: Int, cardToMove: Card, cardToMoveTo: Card) {





    }

}