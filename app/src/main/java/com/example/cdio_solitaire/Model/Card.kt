package com.example.cdio_solitaire.Model



/*
model class for cards.

Rank : 1 = A, 2..10 = 2..10 , 11 = J, 12 = Q, 13 = K
Suit : D = Diamond, H = Heart, C = Club, S = Spade
Downcard is a boolean: true if card picture is face-up, otherwise false
 */

class Card(var rank: Int?, var suit: String?, var isDowncard: Boolean) {
    fun flipCard(){
        isDowncard= !isDowncard
    }
}