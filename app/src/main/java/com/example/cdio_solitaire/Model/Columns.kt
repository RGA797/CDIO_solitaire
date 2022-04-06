package com.example.cdio_solitaire.Model


class Columns() {

        private val bottom_column1: MutableList<Card> = mutableListOf()
        private val bottom_column2: MutableList<Card> = mutableListOf()
        private val bottom_column3: MutableList<Card> = mutableListOf()
        private val bottom_column4: MutableList<Card> = mutableListOf()
        private val bottom_column5: MutableList<Card> = mutableListOf()
        private val bottom_column6: MutableList<Card> = mutableListOf()
        private val bottom_column7: MutableList<Card> = mutableListOf()

        private val top_column1: MutableList<Card> = mutableListOf()
        private val top_column2: MutableList<Card> = mutableListOf()
        private val top_column3: MutableList<Card> = mutableListOf()
        private val top_column4: MutableList<Card> = mutableListOf()

        private val topList: List<MutableList<Card>> = listOf(top_column1,top_column2,top_column3,top_column4)
        private val bottomList:List<MutableList<Card>> = listOf(bottom_column1,bottom_column2,bottom_column3,bottom_column4,bottom_column5,bottom_column6,bottom_column7)

        fun addToBottomList(list: List<Card>, columnIndex: Int){
                var number = 0
                while (number <list.size){
                        bottomList[columnIndex].add(list[number])
                        number++
                }
        }

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
                                else if (j.rank == 1 && !j.isDowncard){
                                        viableIndexes.add(i)
                                }
                                else if (j.rank == 2 && !j.isDowncard){
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

        fun getColumnSize(collumnIndex: Int): Int{
                return bottomList[collumnIndex].size
        }

        fun getColumnsIndexOfCard(card: Card): Int?{
                for (i in 0..6) {
                        for (j in bottomList[i]) {
                                if (j.rank == card.rank && j.suit == card.suit){
                                        return i
                                }
                        }
                }
                return null
        }

        //returns index of Ace or two upcard given a column specified by index
        public fun getCardIndexOfAceOrTwoUpcard(column_index: Int): Int{
                var cardIndex = 0
                for (j in bottomList[column_index]) {
                        if (j.rank == 1 && !j.isDowncard || j.rank == 2 && !j.isDowncard){
                                break
                        }
                        cardIndex++
                }
                return cardIndex
        }
        public fun getNumberOfDowncardsForCard(card: Card): Int {
                var downcards = 0
                for (i in 0..6) {
                        for (j in bottomList[i]) {
                                if (j.rank == card.rank && j.suit == card.suit){
                                        for (x in bottomList[i]){
                                                if (x.isDowncard){
                                                        downcards++
                                                }
                                        }
                                        return downcards
                                }
                        }
                }
                return downcards
        }
        fun getBiggestNumberOfDowncards(): Int{
                var biggestNumber = 0
                for (i in bottomList){
                        var localNumberOfDowncards = 0
                        if (i.isNotEmpty()) {
                                for (j in i){
                                        if (j.isDowncard){
                                                localNumberOfDowncards++
                                        }
                                }
                                if (localNumberOfDowncards > biggestNumber){
                                        biggestNumber = localNumberOfDowncards
                                }
                        }
                }
                return biggestNumber
        }
}