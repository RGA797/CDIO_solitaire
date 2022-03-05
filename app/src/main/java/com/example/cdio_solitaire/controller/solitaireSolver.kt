package com.example.cdio_solitaire.controller

import com.example.cdio_solitaire.Model.Rows

class solitaireSolver {
    val rows = Rows()



    fun canMove(): Boolean{

    }

    fun getRowWithAceOrTwo(): Int?{
        for (i in 0..6) {
            for (j in rows.testList[i]) {
                if (j.isDowncard){
                    continue
                }
                else if (j.rank == "A")
                    return i
                else if (j.rank == "2")
                    return i
            }
        }
        return null
    }
}