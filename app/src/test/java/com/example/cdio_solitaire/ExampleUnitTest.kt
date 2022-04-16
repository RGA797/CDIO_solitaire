package com.example.cdio_solitaire

import com.example.cdio_solitaire.Model.Card
import com.example.cdio_solitaire.controller.SolitaireSolver
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SolutionUnitTest {

    @Test
    fun adding_cards() {
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(6, "S", false))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(9, "H", false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(4, "H", false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(3, "H", false))
        val bottom_column5: MutableList<Card> = mutableListOf(Card(7, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf(Card(11, "H", false))
        val bottom_column7: MutableList<Card> =
            mutableListOf(Card(13, "H", false), Card(8, "S", true))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        solitaireSolver.printList()
    }

    @Test
    fun rule_one_two(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(6, "S", false))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(9, "H", false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(4, "H", false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(3, "H", false))
        val bottom_column5: MutableList<Card> = mutableListOf(Card(7, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf(Card(11, "H", false))
        val bottom_column7: MutableList<Card> = mutableListOf(Card(13, "H", false), Card(8, "S", true))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        solitaireSolver.printSolution()
    }

    @Test
    fun rule_Three(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(6, "S", false), Card(9, "D", true))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(9, "H", false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(4, "H", false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(3, "H", false))
        val bottom_column5: MutableList<Card> = mutableListOf(Card(7, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf(Card(11, "H", false))
        val bottom_column7: MutableList<Card> = mutableListOf(Card(2, "S", false))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        val solution1: MutableList<Card?> = mutableListOf(Card(6, "S", false),Card(7, "H", false))
        val solution2: MutableList<Card?> = mutableListOf(Card(2, "S", false),null)

        val solutionList: MutableList<MutableList<Card?>?> = mutableListOf(solution1,solution2)

        val chosenSolution = solitaireSolver.ruleThree(solutionList)

        assertEquals(solution1, chosenSolution)
    }


    @Test
    fun rule_five(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(6, "S", false), Card(9, "D", false),Card(10, "D", false))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(9, "H", false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(4, "H", false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(3, "H", false))
        val bottom_column5: MutableList<Card> = mutableListOf(Card(7, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf(Card(11, "H", false))
        val bottom_column7: MutableList<Card> = mutableListOf(Card(13, "H", false))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        assertEquals(solitaireSolver.ruleFive(Card(9, "H", false)), true)
        assertEquals(solitaireSolver.ruleFive(Card(13, "H", false)), false)
    }

    @Test
    fun rule_six(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(13, "S", false), Card(9, "D", true),Card(10, "D", true))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(9, "H", false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(4, "H", false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(3, "H", false))
        val bottom_column5: MutableList<Card> = mutableListOf(Card(7, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf()
        val bottom_column7: MutableList<Card> = mutableListOf(Card(13, "H", false), Card(8, "S", true))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        assertEquals(solitaireSolver.ruleSix(Card(13,"H", false)), false)
        assertEquals(solitaireSolver.ruleSix(Card(13,"S", false)), true)

    }

    @Test
    fun solution(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(13, "S", false), Card(9, "D", true),Card(10, "D", true))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(9, "H", false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(4, "H", false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(3, "H", false))
        val bottom_column5: MutableList<Card> = mutableListOf(Card(7, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf()
        val bottom_column7: MutableList<Card> = mutableListOf(Card(13, "H", false), Card(8, "S", true))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        solitaireSolver.printSolution()
    }


}