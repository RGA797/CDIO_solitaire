package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.Model.Columns

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

        //kan vi rykke noget givet algoritmens conditionelle regler
    }

    //returns a move that involves ace or two
    private fun ruleOne(): List<Card?>? {
        val validColumnsIndexes = columns.getColumnsIndexesWithAceOrTwo()
        if (validColumnsIndexes.isNotEmpty()) {
            for (i in validColumnsIndexes) {
                val viableMove = getViableMove(columns.getBottomList()[i][columns.getCardIndexOfAceOrTwoUpcard(i)],false, false, false)
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
        val viableMoves: MutableList<List<Card?>> = ArrayList()
        if (validColumns.isNotEmpty()) {
            for (i in validColumns) {
                val viableMove = getViableMove(columns.getBottomList()[i][columns.getCardIndexOfFirstUpcard(i)],false, false, false)
                if (viableMove != null) {
                    viableMoves.add(viableMove)
                }
            }
            if (viableMoves.isEmpty()){
                return null
            }
            else if (viableMoves.size == 1){
                return viableMoves[0]
            }
            else {
                return ruleThree(viableMoves)
            }
        }
        return null
    }

    //given a list of solutions, ruleThree returns the one which involves the collumn with biggest number
    //of downcards
    private fun ruleThree(viableMoves: MutableList<List<Card?>>): List<Card?>? {
        var biggestNumberOfDowncards: Int? = null
        var currentSolution: List<Card?>? = null
        for (i in viableMoves){
            if (i[0] != null) {
                val downcards = columns.getNumberOfDowncardsForCard(i[0]!!)
                if (biggestNumberOfDowncards == null && currentSolution == null) {
                    biggestNumberOfDowncards = downcards
                    currentSolution = i
                }
                if (downcards > biggestNumberOfDowncards!!) {
                    biggestNumberOfDowncards = downcards
                    currentSolution = i
                }
            }
        }
        return currentSolution
    }

    //function returns true if rule four has not been violated, given a solution
    private fun ruleFour(solution: List<Card?>): Boolean {
        if (columns.columnHasBackrow(columns.getColumnsIndexesOfCard(solution[0]!!)!!)) {
            return true
        }
        val firstColumnSize = columns.getColumnSize(columns.getColumnsIndexesOfCard(solution[1]!!)!!)
        var secondColumnSize = 0
        if (solution[1] != null){
            secondColumnSize = columns.getColumnSize(columns.getColumnsIndexesOfCard(solution[1]!!)!!)
        }
        val difference = firstColumnSize- secondColumnSize
        if (difference != 1 && difference != -1) {
            return true
        }
        return false
    }

    //returns true if there is a king waiting to take a spot after moving a card
    //note: the function assumes that a solution exists
    fun ruleFive(card: Card): Boolean{
        val bottomList = columns.getBottomList()
        var kingWaiting = false
        var emptySpot = false
        var cardFound = false
        for (i in bottomList) {
            if (i.isNotEmpty()) {
                if (!cardFound) {
                    var index = 0
                    for (j in i) {
                        if (j.rank == card.rank && i[0].suit == card.suit) {
                            if (i.size - index == 1) {
                                emptySpot = true
                            }
                            cardFound = true
                            break
                        }
                        index++
                    }
                }
            }
            if (i[0].rank == 13) {
                kingWaiting = true
            }
            if (kingWaiting && emptySpot){
                return true
            }
        }
        return false
    }

    fun ruleSix(card: Card): Boolean {
        return true
        }
    //returns the first found set of cards that can be moved to, otherwise null.
    //first index: from
    //second index: to
    //null in return list means an empty column
    private fun getViableMove(card: Card, useRuleFour: Boolean, useRuleFive: Boolean, useRuleSix: Boolean): List<Card?>? {
        val bottomColumns = columns.getBottomList()
        val topColumns = columns.getTopList()
        var solution: List<Card?>? = null
        //solution moving to bottom card
        for (i in bottomColumns) {
            if (i.isEmpty()) {
                if (isValidMove(card, null, true)) {
                    solution = listOf(card, null)
                }
            }
            else if (i[0].rank == card.rank && i[0].suit == card.suit) {
                continue
            }

            else if (isValidMove(card, i[0], true)) {
                solution = listOf(card, i[0])
                if (useRuleFour){
                    if (!ruleFour(solution)){
                        solution = null
                    }
                }
            }
        }

        //solution moving to top card
        for (j in topColumns) {
            if (j.isEmpty()) {
                if (isValidMove(card, null, false)) {
                    solution = listOf(card, null)
                }
            }
            else if (j[0].rank == card.rank && j[0].suit == card.suit) {
                continue
            } else if (isValidMove(card, j[0], false)) {
                solution = listOf(card, j[0])
            }
        }

        //aditional check given optional rules
        if (useRuleFive){
            if (solution != null){
                if (!ruleFive(solution[0]!!)){
                    solution = null
                }
            }
        }

        //aditional check given optional rules
        if (useRuleSix){
            if (solution != null){
                if (!ruleSix(solution[0]!!)){
                    solution = null
                }
            }
        }
        return solution
    }

    //returns true if given a move that CAN be made ALSO doesnt violate the algorithms conditional rules
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