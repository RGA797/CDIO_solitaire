package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.Model.Columns
import java.lang.IndexOutOfBoundsException

//controller class meant to follow the ruling given in following article: https://www.chessandpoker.com/solitaire_strategy.html
//uses column model class
class SolitaireSolver {
    private val columns = Columns()
    private var bottomSolutions: MutableList<MutableList<Card?>?> = mutableListOf()
    private var topSolutions: MutableList<MutableList<Card?>?> = mutableListOf()

    //adds every element of a list of cards to bottom column
    fun addCards (list: List<Card>, columnIndex: Int){
        columns.addToBottomList(list, columnIndex)
    }

    fun printList(){
        for (i in columns.getBottomList()){
            var string = "["
            if (i.isNotEmpty()){
                for (j in i){
                    string = string + "{" + j.rank + ", " + j.suit + "}" + ","
                }
            }
            string.dropLast(2)
            string = "$string]"
            println(string)
        }
    }

    //prints the solution the algorithm has found
    fun printSolution(){
        val solution = solve()
        if (solution != null){
            if (solution[1] != null){
                println("solution!: " + solution[0]!!.rank + solution[0]!!.suit + " to " + solution[1]!!.rank + solution[1]!!.suit )
            }
            else{
                println("solution!: " + solution[0]!!.rank + solution[0]!!.suit + " to an empty column")
            }
        }
        else{
            println("no solution found!" )
        }
    }

    //solution algorithm. follows rules of the mentioned article
    fun solve(): List<Card?>? {

        //if a solution exists that follows rule 1, return it
        var solution = ruleOne()
        if (solution != null) {
            return solution
        }

        //if a solution exists that follows rule 2, return it
        solution = ruleTwo()
        if (solution != null) {
            return solution
        }

        //returns the optimal solution that follows the rules (if it exists)
        //prioritizes bottom to bottom transfers, then bottom to top.
        solution = generalSolution()
        if (solution != null) {
            return solution
        }

        //if no optimal solution found, try moving any bottom card to top column
        solution = generalSolution()
        if (solution != null) {
            return solution
        }

        return solution
    }

    //returns a move that involves ace or two
    private fun generalSolution(): List<Card?>? {
        val bottomList = columns.getBottomList()
        if (bottomList.isNotEmpty()) {
            for (i in bottomList) {
                for (j in i){
                    if (!j.isDowncard) {
                        val viableMove = getViableMove(
                            j,
                            useRuleFour = true,
                            useRuleFive = true,
                            useRuleSix = true,
                            useRuleSeven = true,
                            useRuleEight = true,
                            useRuleNine = true,
                        )
                        if (viableMove != null) {
                            return (viableMove)
                        }
                    }
                }
            }
        }
        return null
    }

    //EVERY RULE FUNCTION HERE (BESIDES RULE 1 AND 2) WILL RETURN A BOOLEAN. TRUE IF A GIVEN LIST OF CARDS (A SOLUTION) VIOLATES THE SPECIFIC RULE

    //returns a move that involves ace or two
    private fun ruleOne(): List<Card?>? {
        val validColumnsIndexes = columns.getColumnsIndexesWithAceOrTwo()
        if (validColumnsIndexes.isNotEmpty()) {
            for (i in validColumnsIndexes) {
                val viableMove = getViableMove(columns.getBottomList()[i][columns.getCardIndexOfAceOrTwoUpcard(i)],false, false, false, false, false, false )
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
        val viableMoves: MutableList<MutableList<Card?>?> = mutableListOf()
        if (validColumns.isNotEmpty()) {
            for (i in validColumns) {
                val viableMove = getViableMove(columns.getBottomList()[i][columns.getCardIndexOfFirstUpcard(i)],false, false, false, false, false, false )
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
    fun ruleThree(viableMoves: MutableList<MutableList<Card?>?>): MutableList<Card?>? {
        var biggestNumberOfDowncards: Int? = null
        var currentSolution: MutableList<Card?>? = null
        for (i in viableMoves){
            if (i?.get(0) != null) {
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
        if (columns.columnHasBackrow(columns.getColumnsIndexOfCard(solution[0]!!)!!)) {
            return true
        }
        val firstColumnSize = columns.getColumnSize(columns.getColumnsIndexOfCard(solution[1]!!)!!)
        var secondColumnSize = 0
        if (solution[1] != null){
            secondColumnSize = columns.getColumnSize(columns.getColumnsIndexOfCard(solution[1]!!)!!)
        }

        val difference = firstColumnSize- secondColumnSize
        if (difference != 1 && difference != -1 && difference == 0) {
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
                if (!cardFound || !kingWaiting) {
                    var index = 0
                    for (j in i) {
                        if (j.rank == card.rank && j.suit == card.suit) {
                            if (i.size - index == 1) {
                                emptySpot = true
                            }
                            cardFound = true
                        }

                        if (j.rank == 13 && !j.isDowncard) {
                            if (j.rank == card.rank && j.suit == card.suit){
                                continue
                            }
                            else{
                                kingWaiting = true
                            }
                        }

                        if (cardFound && kingWaiting){
                            break
                        }
                        index++
                    }
                }
            }
            if (kingWaiting && emptySpot){
                return true
            }
        }
        if (cardFound && !emptySpot){
            return true
        }
        return false
    }

    fun ruleSix(card: Card): Boolean {
        if (card.rank != 13) {
            return false
        }
        if (columns.getNumberOfDowncardsForCard(card) == columns.getBiggestNumberOfDowncards()){
            return true
        }
        return false
    }

    fun ruleSeven(solution: MutableList<Card?>?): Boolean {


        //Allow a play that frees a downcard
        if (solution?.get(0)?.let { allowsFreedDowncard(it) }!!){
            return true
        }

        //Clear a spot for an IMMEDIATE waiting King (it cannot be to simply clear a spot)
        if (ruleFive(solution[0]!!)){
            return true
        }

        return false
    }

    fun ruleEight(solution: List<Card?>?): Boolean{
        if (solution != null){
            if (solution[0]!!.rank  != 5 && solution[0]!!.rank != 6 &&  solution[0]!!.rank  != 7  && solution[0]!!.rank  != 8){
                return true
            }

            //It will allow a play or transfer that will IMMEDIATELY free a downcard
            if (solution[0]?.let { allowsFreedDowncard(it) }!!){
                return true
            }

            //if no other choice exists you have to accept
            var otherChoise = false
            for (i in bottomSolutions.indices){
                if (bottomSolutions[i] != null){

                    //if card moving from is different than given solution
                    if (bottomSolutions[i]?.get(0)?.rank  != solution[0]?.rank || bottomSolutions[i]?.get(0)?.suit  != solution[0]?.suit) {
                        otherChoise = true
                    }

                    //if card moving to is different than given solution
                    if (bottomSolutions[i]?.get(1)?.rank  != solution[1]?.rank || bottomSolutions[i]?.get(1)?.suit  != solution[1]?.suit){
                        otherChoise = true
                    }
                }
            }
            if (!otherChoise){
                return true
        }
    }

        return false
    }


    //returns true if a given card and its cardindex
    fun freesDowncard(card: Card, cardIndex: Int): Boolean {
        var willFree = false
        val index = columns.getColumnsIndexOfCard(card)
        if (index != null) {
            try {
                if (columns.getBottomList()[index][cardIndex-1].isDowncard) {
                    willFree = true
                }
            }catch (e: IndexOutOfBoundsException){
                return false
            }
        }
        return willFree
    }

    fun allowsFreedDowncard (card: Card): Boolean{
        var allowsFree = false
        val index = columns.getColumnsIndexOfCard(card)
        if (index != null) {
            if (columns.getBottomList()[index].size > 1) {
                val cardToAllow = columns.getBottomList()[index][1]
                if (!cardToAllow.isDowncard) {
                    val viableMove = getViableMove(
                        card,
                        useRuleFour = true,
                        useRuleFive = true,
                        useRuleSix = true,
                        useRuleSeven = true,
                        useRuleEight = true,
                        useRuleNine = true,
                    )
                    if (viableMove != null){
                        if (viableMove[0]?.let { freesDowncard(it, 1) } != null){
                            allowsFree = true
                        }
                    }
                }
            }
        }
        return allowsFree
    }

    fun sameColorFreed(card: Card): Boolean{
        var sameColor = false
        val index = columns.getColumnsIndexOfCard(card)
        val bottomList = columns.getBottomList()

        if (index != null) {
            for (i in bottomList[index].indices){
                if (bottomList[index][i].rank == card.rank && bottomList[index][i].suit == card.suit ){
                    if (i != 0){
                        if (bottomList[index][i-1].suit == "D" || bottomList[index][i-1].suit == "D"){
                            if (card.suit == "D" || card.suit == "D"){
                                sameColor = true
                            }
                        }

                        if (bottomList[index][i-1].suit == "S" || bottomList[index][i-1].suit == "C"){
                            if (card.suit == "S" || card.suit == "C"){
                                sameColor = true
                            }
                        }
                    }
                }
            }
        }
        return sameColor
    }


    //returns the first found set of cards that can be moved to, otherwise null.
    //first index: from
    //second index: to
    //null in return list means an empty column
    private fun getViableMove(card: Card, useRuleFour: Boolean, useRuleFive: Boolean, useRuleSix: Boolean, useRuleSeven: Boolean, useRuleEight: Boolean, useRuleNine: Boolean): MutableList<Card?>? {
        val bottomColumns = columns.getBottomList()
        val topColumns = columns.getTopList()
        var currentSolution: MutableList<Card?>?
        //solution moving to bottom card
        for (i in bottomColumns) {
            if (i.isEmpty()) {
                if (isValidMove(card, null, true)) {
                    bottomSolutions.add (mutableListOf(card, null))
                }
            }
            else if (i[0].rank == card.rank && i[0].suit == card.suit) {
                continue
            }
            
            else if (isValidMove(card, i[0], true)) {
                currentSolution = mutableListOf(card, i[0])
                if (useRuleFour){
                    if (!ruleFour(currentSolution)){
                        currentSolution = null
                    }
                }
                if (currentSolution != null){
                    bottomSolutions.add(currentSolution)
                }

            }
        }

        //solution moving to top card
        for (j in topColumns) {
            if (j.isEmpty()) {
                if (isValidMove(card, null, false)) {
                    topSolutions.add(mutableListOf(card, null))
                }
            }
            else if (j[0].rank == card.rank && j[0].suit == card.suit) {
                continue
            } else if (isValidMove(card, j[0], false)) {
                topSolutions.add(mutableListOf(card, j[0]))
            }

            if (useRuleSeven){
                if (topSolutions.isNotEmpty()) {
                    for (i in topSolutions.indices) {
                        if (ruleSeven(topSolutions[i]) == false) {
                            bottomSolutions.removeAt(i)
                        }
                    }
                }
            }

        }

        //aditional check given optional rules
        if (useRuleFive){
            for (i in bottomSolutions.indices) {
                if (bottomSolutions[i] != null) {
                    if (!ruleFive(bottomSolutions[i]?.get(0)!!)) {
                        bottomSolutions[i] = null
                    }
                }
            }
        }

        //aditional check given optional rules
        if (useRuleSix){
            for (i in bottomSolutions.indices) {
                if (bottomSolutions[i] != null) {
                    if (bottomSolutions[i]?.get(0)?.rank == 13) {
                        if (!ruleSix(bottomSolutions[i]?.get(0)!!)) {
                            bottomSolutions[i] = null
                        }
                    }
                }
            }
        }

        if (useRuleEight){
            for (i in bottomSolutions.indices) {
                if (bottomSolutions[i] != null) {
                    if (bottomSolutions[i]?.get(0)?.rank == 13) {
                        if (!bottomSolutions[i]?.let { ruleEight(it) }!!) {
                            bottomSolutions[i] = null
                        }
                    }
                }
            }
        }
        //remove null values from solution list (solutions that violate algorithm rules)
        for (i in bottomSolutions.indices){
            if (bottomSolutions[i] == null){
                bottomSolutions.removeAt(i)
            }
        }


        //the order in which solutions are given as the "optimal play", is decided to be bottom solutions before top solutions

        //if given a choice(bottom solution), use rule 3
        if (bottomSolutions.size > 1){
            return ruleThree(bottomSolutions)
        }

        //if only one choice. return it
        if (bottomSolutions.size == 1){
            return bottomSolutions[0]
        }

        //if given a choice (top solution), use rule 3
        if (topSolutions.size > 1){
            return ruleThree(topSolutions)
        }

        //if only one choice. return it
        if (topSolutions.size == 1){
            return bottomSolutions[0]
        }

        //if no solutions at all. return null
        return null
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