package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.Model.Columns
import org.json.JSONObject.NULL

class SolitaireSolver {
    val columns = Columns()

    fun solve(): List<Card>? {

        //kan vi bruge rule 1?
        var solution = ruleOne()
        if (ruleOne() != null){
            return solution
        }
        //kan vi bruge rule 2?
        solution = ruleTwo()
        if (ruleTwo() != null ){
            return  solution
        }
        return null
    }

    //returns a move that involves ace or two
    fun ruleOne():  List<Card>? {
        val validColumns = columns.getColumnsWithAceOrTwo()
        if (validColumns.isNotEmpty()){
            for (i in validColumns){
                val viableMove = getViableMove(columns.getBottomList()[i][0])
                if (viableMove != null){
                    return (viableMove)
                }
            }
        }
        return null
    }

    //returns a move that can free up another card
    fun ruleTwo(): List<Card>? {
        val validColumns = columns.getBottomColumnsIndexesWithBackrow()
        if (validColumns.isNotEmpty()){
            for (i in validColumns){
                if (columns.columnHasBackrow(i)){
                    val viableMove = getViableMove(columns.getBottomList()[i][columns.getCardIndexOfFirstUpcard(i)])
                    if (viableMove != null){
                        return viableMove
                    }
                }
            }
        }
        return null
    }

    fun ruleSix(): List<Card>? {
        return null
    }

    //returns the first found set of cards that can be moved to, otherwise null.
    //first index: from
    //second index: to
    fun getViableMove(card: Card): List<Card>? {
        val bottomColumn = columns.getBottomList()
        for (i in bottomColumn){
            if (i[0] == card || i.isEmpty()){
                continue
            }
            else if (isValidMove(card,i[0])){
                return listOf(card, i[0])
            }
        }
        return null
    }


    fun isValidMove(cardToMove: Card, cardToMoveTo: Card): Boolean {
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

    fun isValidMoveTopColumn(cardToMove: Card, cardToMoveTo: Card): Boolean {
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