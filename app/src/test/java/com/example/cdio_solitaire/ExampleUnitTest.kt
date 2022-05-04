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
    fun rule_Four(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(6, "S", false), Card(null, null, true))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(7, "H", false), Card(null, null, false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(8, "S", false), Card(null, null, false), Card(null, null, false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(9, "H", false),)
        val bottom_column5: MutableList<Card> = mutableListOf(Card(13, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf(Card(11, "H", false))
        val bottom_column7: MutableList<Card> = mutableListOf(Card(12, "H", false))
            mutableListOf(Card(13, "H", false), Card(8, "S", true))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        val solution1: MutableList<Card?> = mutableListOf(Card(6, "S", false),Card(7, "H", false))
        val solution2: MutableList<Card?> = mutableListOf(Card(7, "H", false),Card(8, "S", false))
        val solution3: MutableList<Card?> = mutableListOf(Card(8, "S", false),Card(9, "H", false))

        assertEquals(solitaireSolver.ruleFour(solution1), true)
        assertEquals(solitaireSolver.ruleFour(solution2), false)
        assertEquals(solitaireSolver.ruleFour(solution3), true)
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
    fun rule_Seven(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(6, "S", false), Card(6, "C", false), Card(null,null,true))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(7, "S", false), Card(7, "C", true), Card(null,null,true))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(10, "C", false))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(9, "S", false))
        val bottom_column5: MutableList<Card> =  mutableListOf(Card(7, "D", false), Card(7, "H", false), Card(null,null,false))
        val bottom_column6: MutableList<Card> = mutableListOf(Card(8, "D", false), Card(8, "H", false), Card(null,null,true))
        val bottom_column7: MutableList<Card> = mutableListOf()

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        val solution1: MutableList<Card?> = mutableListOf(Card(6, "S", false),null)
        val solution2: MutableList<Card?> = mutableListOf(Card(7, "S", false),null)
        val solution3: MutableList<Card?> = mutableListOf(Card(7, "D", false),null)
        val solution4: MutableList<Card?> = mutableListOf(Card(8, "D", false),null)


        assertEquals(solitaireSolver.allowsFreedDowncard(solution1[0]!!), true)
        assertEquals(solitaireSolver.allowsFreedDowncard(solution2[0]!!), false)
        assertEquals(solitaireSolver.allowsFreedDowncard(solution3[0]!!), false)
        assertEquals(solitaireSolver.allowsFreedDowncard(solution4[0]!!), true)
    }


    @Test
    fun rule_Eight(){
        val solitaireSolver = SolitaireSolver()
        val bottom_column1: MutableList<Card> = mutableListOf(Card(7, "S", false), Card(null, null, false))
        val bottom_column2: MutableList<Card> = mutableListOf(Card(8, "H", false), Card(null, null, false))
        val bottom_column3: MutableList<Card> = mutableListOf(Card(12, "S", false), Card(null, null, true))
        val bottom_column4: MutableList<Card> = mutableListOf(Card(9, "H", false), Card(null, null, true))
        val bottom_column5: MutableList<Card> = mutableListOf(Card(13, "H", false))
        val bottom_column6: MutableList<Card> = mutableListOf(Card(11, "H", false))
        val bottom_column7: MutableList<Card> = mutableListOf(Card(12, "H", false))
        mutableListOf(Card(13, "H", false), Card(8, "S", true))

        solitaireSolver.addCards(bottom_column1, 0)
        solitaireSolver.addCards(bottom_column2, 1)
        solitaireSolver.addCards(bottom_column3, 2)
        solitaireSolver.addCards(bottom_column4, 3)
        solitaireSolver.addCards(bottom_column5, 4)
        solitaireSolver.addCards(bottom_column6, 5)
        solitaireSolver.addCards(bottom_column7, 6)

        val solution1: MutableList<Card?> = mutableListOf(Card(7, "S", false),Card(8, "H", false))
        val solution2: MutableList<Card?> = mutableListOf(Card(12, "S", false),Card(13, "H", false))

        //solution 1, and its the only solution
        var bottomSolutions:MutableList<MutableList<Card?>?> = mutableListOf(solution1)
        assertEquals(solitaireSolver.ruleEight(solution1, bottomSolutions), true)

        //solution 1, but there exists another
        bottomSolutions = mutableListOf(solution1, solution2)
        assertEquals(solitaireSolver.ruleEight(solution1, bottomSolutions), false)

        //solution 2, and there exists another
        bottomSolutions = mutableListOf(solution1, solution2)
        assertEquals(solitaireSolver.ruleEight(solution2, bottomSolutions), true)

        //null solution
        bottomSolutions = mutableListOf(null)
        assertEquals(solitaireSolver.ruleEight(null, bottomSolutions), false)
    }


    //does 100.000 random playsets of random cards and sees how many can be solved using the solver.
    //based on almost nothing, we determine that the algorithm should have a solution at least 50% of the time
    //cards are completely random, and CAN have duplicates, which isn't ideal but its what we have.
    //Results are VERY interesting. apparently its almost always works around 87% of the times no matter how many times its run, AND 85% of the time its within the algorithm rulings.
    @Test
    fun solution(){
        var solutions = 0
        val suits = listOf("S", "C", "H", "D")
        for (i in 1..100000) {
            val solitaireSolver = SolitaireSolver()
            val bottom_column1: MutableList<Card> = mutableListOf(Card((0..10).random(), suits[(0..3).random()], false))
            val bottom_column2: MutableList<Card> = mutableListOf(Card((0..10).random(), suits[(0..3).random()], false), Card((0..10).random(), suits[(0..3).random()], true))
            val bottom_column3: MutableList<Card> = mutableListOf(Card((0..10).random(), suits[(0..3).random()], false), Card((0..10).random(), suits[(0..3).random()], true), Card((0..10).random(), suits[(0..3).random()], true) )
            val bottom_column4: MutableList<Card> = mutableListOf(Card((0..10).random(), suits[(0..3).random()], false), Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) )
            val bottom_column5: MutableList<Card> = mutableListOf(Card((0..10).random(), suits[(0..3).random()], false), Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) )
            val bottom_column6: MutableList<Card> = mutableListOf(Card((0..10).random(), suits[(0..3).random()], false), Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) )
            val bottom_column7: MutableList<Card> = mutableListOf(Card((0..10).random(), suits[(0..3).random()], false), Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) , Card((0..10).random(), suits[(0..3).random()], true) )


            solitaireSolver.addCards(bottom_column1, 0)
            solitaireSolver.addCards(bottom_column2, 1)
            solitaireSolver.addCards(bottom_column3, 2)
            solitaireSolver.addCards(bottom_column4, 3)
            solitaireSolver.addCards(bottom_column5, 4)
            solitaireSolver.addCards(bottom_column6, 5)
            solitaireSolver.addCards(bottom_column7, 6)

            if (solitaireSolver.solve() != null){
                solutions++
            }
        }
        //println(solutions)

        assertEquals(solutions > 50000, true)
    }
}