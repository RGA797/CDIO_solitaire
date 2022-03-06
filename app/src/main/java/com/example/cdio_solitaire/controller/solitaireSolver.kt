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

    fun moveCard(i: Int, card: Card) {
        var cardColor : Boolean //black -> true, red -> false
        if (card.suit == "S" || card.suit == "C"){//black card
            cardColor = true
        } else if (card.suit =="D" || card.suit =="H"){ //red card
            cardColor = false
        }




    }

}