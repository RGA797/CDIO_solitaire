package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.Model.Columns
import org.json.JSONObject.NULL

class SolitaireSolver {
    private val columns = Columns()

    public fun solve(): List<Card?>? {

        //kan vi bruge rule 1?
        var solution = ruleOne()
        if (ruleOne() != null) {
            return solution
        }
        //kan vi bruge rule 2?
        solution = ruleTwo()
        if (ruleTwo() != null) {
            return solution
        }
        return null
    }

    //returns a move that involves ace or two
    private fun ruleOne(): List<Card?>? {
        val validColumnsIndexes = columns.getColumnsIndexesWithAceOrTwo()
        if (validColumnsIndexes.isNotEmpty()) {
            for (i in validColumnsIndexes) {
                val viableMove = getViableMove(columns.getBottomList()[i][columns.getCardIndexOfAceOrTwo(i)])
                if (viableMove != null) {
                    return (viableMove)
                }
            }
        }
        return null
    }

    //returns a move that can free up another card
    private fun ruleTwo(): List<Card?>? {
        val validColumns = columns.getBottomColumnsIndexesWithBackrow()
        if (validColumns.isNotEmpty()) {
            for (i in validColumns) {
                val viableMove = getViableMove(columns.getBottomList()[i][columns.getCardIndexOfFirstUpcard(i)])
                if (viableMove != null) {
                    return viableMove
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
    //null in return list means an empty column
    private fun getViableMove(card: Card): List<Card?>? {
        val bottomColumns = columns.getBottomList()
        val topColumns = columns.getTopList()
        for (i in bottomColumns) {
            if (i.isEmpty()) {
                if (isValidMove(card, null, true)) {
                    return listOf(card, null)
                }
            }
            else if (i[0].rank == card.rank && i[0].suit == card.suit) {
                continue
            }
            else if (isValidMove(card, i[0], true)) {
                return listOf(card, i[0])
            }
        }

        for (j in topColumns) {
            if (j.isEmpty()) {
                if (isValidMove(card, null, false)) {
                    return listOf(card, null)
                }
            }
            else if (j[0].rank == card.rank && j[0].suit == card.suit) {
                continue
            } else if (isValidMove(card, j[0], false)) {
                return listOf(card, j[0])
            }
        }
        return null
    }

    private fun isValidMove(cardToMove: Card, cardToMoveTo: Card?, bottomRules: Boolean): Boolean {
        var suitMoveValid = false
        var rankMoveValid = false

        if (bottomRules) {
            if (cardToMoveTo == null && cardToMove.rank == 13) {
                return true
            }
            if (cardToMoveTo != null) {
                if ((cardToMove.suit == "S" || cardToMove.suit == "C") && (cardToMoveTo.suit == "D" || cardToMoveTo.suit == "H")) {//black card
                    suitMoveValid = true
                } else if ((cardToMove.suit == "D" || cardToMove.suit == "H") && (cardToMoveTo.suit == "S" || cardToMoveTo.suit == "C")) { //red card
                    suitMoveValid = true
                }

                if (cardToMove.rank + 1 == cardToMoveTo.rank) { //Rank move valid
                    rankMoveValid = true
                }

                if (suitMoveValid && rankMoveValid) {
                    return true
                }
            }
        }

        else if (!bottomRules) {
            if (cardToMoveTo == null && cardToMove.rank == 1) {
                return true
            }
            if (cardToMoveTo != null) {
                if (cardToMove.suit == cardToMoveTo.suit) {
                    suitMoveValid = true
                }

                if (cardToMove.rank - 1 == cardToMoveTo.rank) { //Rank move valid
                    rankMoveValid = true
                }

                if (suitMoveValid && rankMoveValid) {
                    return true
                }
            }
        }
        return false
    }
}