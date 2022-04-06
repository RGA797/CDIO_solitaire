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
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        assertEquals(4, 2 + 2)
    }

    @Test
    fun adding_cards() {
        var solitaireSolver = SolitaireSolver()
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
        var solitaireSolver = SolitaireSolver()
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

        val solution = solitaireSolver.solve()
        if (solution != null){
            if (solution[1] != null){
                println("solution!: " + solution[0]!!.rank + solution[0]!!.suit + ", " + solution[1]!!.rank + solution[1]!!.suit )
            }
            else{
                println("solution!: " + solution[0]!!.rank + solution[0]!!.suit + ", an empty column")
            }
        }
        else{
            println("no solution found!" )
        }
    }

    @Test
    fun rule_five(){
        var solitaireSolver = SolitaireSolver()
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





        if (solitaireSolver.ruleFive(Card(6, "S", false))){
            println("solution found!" )
        }
        else{
            println("no solution found")
        }
    }



}