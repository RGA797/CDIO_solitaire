package com.example.cdio_solitaire.Model

class Card(var rank: String, var suit: String, var isDowncard: Boolean) {
    @JvmName("getRank1")
    fun getRank(): String {
        return rank
    }
    @JvmName("getSuit1")
    fun getSuit(): String {
        return suit
    }
    fun flipCard(){
        isDowncard= !isDowncard
    }
}