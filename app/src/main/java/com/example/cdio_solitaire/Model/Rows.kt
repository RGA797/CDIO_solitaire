package com.example.cdio_solitaire.Model

class Rows() {

        val bottom_row1: List<Card> = listOf(Card(1, "H", false))
        val bottom_row2: List<Card> = listOf(Card(2, "H", false))
        val bottom_row3: List<Card> = listOf(Card(2, "C", false))
        val bottom_row4: List<Card> = listOf(Card(3, "C", false))
        val bottom_row5: List<Card> = listOf(Card(10, "S", false))
        val bottom_row6: List<Card> = listOf(Card(11, "D", false))
        val bottom_row7: List<Card> = listOf(Card(13, "D", false))

        val top_row1: List<Card> = listOf()
        val top_row2: List<Card> = listOf()
        val top_row3: List<Card> = listOf()
        val top_row4: List<Card> = listOf()

        val topList: List<List<Card>> = listOf(top_row1,top_row2,top_row3,top_row4)
        val bottomList:List<List<Card>> = listOf(bottom_row1,bottom_row2,bottom_row3,bottom_row4,bottom_row5,bottom_row6,bottom_row7)
}