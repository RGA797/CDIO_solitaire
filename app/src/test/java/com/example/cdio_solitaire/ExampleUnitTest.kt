package com.example.cdio_solitaire

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
    fun rule_one_two(){
        var solitaireSolver = SolitaireSolver()
        val solution = solitaireSolver.solve()
        if (solution != null){
        println("solution!: " + solution[0].rank + solution[0].suit + ", " + solution[1].rank + solution[1].suit )
        }
    }


}