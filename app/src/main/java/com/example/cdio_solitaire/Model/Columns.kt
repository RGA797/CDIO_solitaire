package com.example.cdio_solitaire.Model

class Columns() {

        private val bottom_column1: List<Card> = listOf(Card(5, "H", false), Card(1, "H", true))
        private val bottom_column2: List<Card> = listOf(Card(6, "H", false))
        private val bottom_column3: List<Card> = listOf(Card(9, "C", false))
        private val bottom_column4: List<Card> = listOf(Card(3, "C", false))
        private val bottom_column5: List<Card> = listOf(Card(7, "S", false))
        private val bottom_column6: List<Card> = listOf(Card(11, "D", false))
        private val bottom_column7: List<Card> = listOf(Card(13, "D", false))

        private val top_column1: List<Card> = listOf()
        private val top_column2: List<Card> = listOf()
        private val top_column3: List<Card> = listOf()
        private val top_column4: List<Card> = listOf()

        private val topList: List<List<Card>> = listOf(top_column1,top_column2,top_column3,top_column4)
        private val bottomList:List<List<Card>> = listOf(bottom_column1,bottom_column2,bottom_column3,bottom_column4,bottom_column5,bottom_column6,bottom_column7)



        fun getBottomList(): List<List<Card>> {
                return bottomList
        }

        fun columnHasBackrow(column_index: Int): Boolean {
                for (j in bottomList[column_index]) {
                        if (j.isDowncard){
                                return true
                        }
                }
                return false
        }

        fun getCardIndexOfFirstUpcard(column_index: Int): Int {
                var cardIndex = 0
                for (j in bottomList[column_index]) {
                        if (!j.isDowncard) {
                                break
                        }
                        cardIndex++
                }
                return cardIndex
        }

        fun getColumnsWithAceOrTwo(): MutableList<Int> {
                val viableIndexes = mutableListOf<Int>()
                for (i in 0..6) {
                        for (j in bottomList[i]) {
                                if (j.isDowncard) {
                                        continue
                                }
                                else if (j.rank == 1){
                                        viableIndexes.add(i)
                                }
                                else if (j.rank == 2){
                                        viableIndexes.add(i)
                                }
                        }
                }
                        return viableIndexes
        }

        fun getLongestBottomColumnsIndexes(): MutableList<Int> {
                var longest_backrow_size = 0
                var longest_column_indexes = mutableListOf<Int>()
                for (collumn_index in 0..6) {
                        var local_column_length = 0
                        for (j in bottomList[collumn_index]) {
                                local_column_length ++
                        }

                        if (local_column_length == longest_backrow_size){
                                longest_column_indexes.add(collumn_index)
                        }

                        if (local_column_length > longest_backrow_size){
                                longest_column_indexes = mutableListOf()
                                longest_backrow_size = local_column_length
                                longest_column_indexes.add(collumn_index)
                        }
                }
                return  longest_column_indexes

        }
        fun getBottomColumnsIndexesWithBackrow(): MutableList<Int> {
                var validIndexes = mutableListOf<Int>()
                for (collumn_index in 0..6) {
                        for (card in bottomList[collumn_index]) {
                                if (card.isDowncard){
                                        validIndexes.add(collumn_index)
                                        break
                                }
                        }
                }
                return  validIndexes
        }

        fun getCardIndexOfAceOrTwo(column_index: Int): Int{
                var cardIndex = 0
                for (j in bottomList[column_index]) {
                        if (j.rank == 1 || j.rank == 2){
                                break
                        }
                        cardIndex++
                }
                return cardIndex
        }
}