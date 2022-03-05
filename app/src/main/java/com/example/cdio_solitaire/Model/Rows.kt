package com.example.cdio_solitaire.Model

class Rows() {

        val row1: List<Card> = listOf(Card("A", "H", false))
        val row2: List<Card> = listOf(Card("2", "H", false))
        val row3: List<Card> = listOf(Card("2", "C", false))
        val row4: List<Card> = listOf(Card("3", "C", false))
        val row5: List<Card> = listOf(Card("10", "S", false))
        val row6: List<Card> = listOf(Card("J", "D", false))
        val row7: List<Card> = listOf(Card("K", "D", false))
        val testList:List<List<Card>> = listOf(row1,row2,row3,row4,row5,row6,row7)
}