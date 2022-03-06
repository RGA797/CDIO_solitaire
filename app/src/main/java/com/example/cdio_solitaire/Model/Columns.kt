package com.example.cdio_solitaire.Model

class Columns() {

        val bottom_column1: List<Card> = listOf(Card(1, "H", false))
        val bottom_column2: List<Card> = listOf(Card(2, "H", false))
        val bottom_column3: List<Card> = listOf(Card(2, "C", false))
        val bottom_column4: List<Card> = listOf(Card(3, "C", false))
        val bottom_column5: List<Card> = listOf(Card(10, "S", false))
        val bottom_column6: List<Card> = listOf(Card(11, "D", false))
        val bottom_column7: List<Card> = listOf(Card(13, "D", false))

        val top_column1: List<Card> = listOf()
        val top_column2: List<Card> = listOf()
        val top_column3: List<Card> = listOf()
        val top_column4: List<Card> = listOf()

        val topList: List<List<Card>> = listOf(top_column1,top_column2,top_column3,top_column4)
        val bottomList:List<List<Card>> = listOf(bottom_column1,bottom_column2,bottom_column3,bottom_column4,bottom_column5,bottom_column6,bottom_column7)
}