package com.example.cdio_solitaire.Model

import android.R.array




class Columns() {

        private val bottom_column1: List<Card> = listOf(Card(6, "H", false), Card(1, "H", true))
        private val bottom_column2: List<Card> = listOf(Card(5, "H", false))
        private val bottom_column3: List<Card> = listOf(Card(9, "H", false))
        private val bottom_column4: List<Card> = listOf(Card(1, "H", false))
        private val bottom_column5: List<Card> = listOf(Card(7, "H", false))
        private val bottom_column6: List<Card> = listOf(Card(11, "H", false))
        private val bottom_column7: List<Card> = listOf(Card(13, "H", false))

        private val top_column1: List<Card> = listOf()
        private val top_column2: List<Card> = listOf()
        private val top_column3: List<Card> = listOf()
        private val top_column4: List<Card> = listOf()

        private val topList: List<List<Card>> = listOf(top_column1,top_column2,top_column3,top_column4)
        private val bottomList:List<List<Card>> = listOf(bottom_column1,bottom_column2,bottom_column3,bottom_column4,bottom_column5,bottom_column6,bottom_column7)



        public fun getBottomList(): List<List<Card>> {
                return bottomList
        }

        public fun getTopList(): List<List<Card>> {
                return topList
        }

        public fun columnHasBackrow(column_index: Int): Boolean {
                for (j in bottomList[column_index]) {
                        if (j.isDowncard){
                                return true
                        }
                }
                return false
        }

        //returns true if rule Five would not be violated given moving a card
        public fun ruleFive(card: Card, cardIndex: Int): Boolean{
                for (j in bottomList) {
                        if (j[0].rank == 13){
                                return true
                        }
                        else if (j[cardIndex].rank == card.rank && j[cardIndex].rank == card.rank) {
                                try {
                                        if (j[cardIndex+1].rank == 13){
                                                return true
                                        }
                                } catch (exception: ArrayIndexOutOfBoundsException) {
                                        return false
                                }
                        }
                }
                return false
        }


        public fun getCardIndexOfFirstUpcard(column_index: Int): Int {
                var cardIndex = 0
                for (j in bottomList[column_index]) {
                        if (!j.isDowncard) {
                                break
                        }
                        cardIndex++
                }
                return cardIndex
        }

        public fun getColumnsIndexesWithAceOrTwo(): MutableList<Int> {
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

        public fun getLongestBottomColumnsIndexes(): MutableList<Int> {
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

        public fun getBottomColumnsIndexesWithBackrow(): MutableList<Int> {
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

        public fun getCardIndexOfAceOrTwo(column_index: Int): Int{
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