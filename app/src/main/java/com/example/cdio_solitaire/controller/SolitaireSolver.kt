package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.Model.Columns
import org.json.JSONObject.NULL

class SolitaireSolver {
    val columns = Columns()

    fun solve(): String{

        //kan vi bruge rule 1
        if (ruleOne() != null){

        }

        //kan vi bruge rule 2
        else if (ruleTwo() != null ){

        }
        //

        //



        return "solution"
    }


    //returns the card with most backturned cards behind it
    fun ruleTwo(): MutableList<Card?> {
        val longestColumns = getLongestBottomColumns()

        for (i in longestColumns){
            if (columnHasBackrow(i)){
                return (columns.bottomList[i][0])
            }
        }
        return null

    }

    fun columnHasBackrow(column_index: Int): Boolean {
        for (j in columns.bottomList[column_index]) {
            if (j.isDowncard){
                return true
            }
        }
        return false
    }

    fun getLongestBottomColumns(): MutableList<Int> {
        var longest_backrow_size = 0
        var longest_column_indexes = mutableListOf<Int>()
        for (collumn_index in 0..6) {
            var local_column_length = 0
            for (j in columns.bottomList[collumn_index]) {
                local_column_length ++
            }

            if (local_column_length == longest_backrow_size){
                longest_column_indexes.add(collumn_index)
            }

            if (local_column_length > longest_backrow_size){
                longest_column_indexes = mutableListOf()
                longest_backrow_size = local_column_length
                longest_column_indexes.add(collumn_index)
            }
        }
        return  longest_column_indexes
    }

    fun ruleOne(): Card? {
        val validColumn = getColumnWithAceOrTwo()
        if (validColumn!= null){
            return columns.bottomList[validColumn][0]
        }
        else{
            return null
        }
    }

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