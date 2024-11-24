package com.example.m5individual13.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.m5individual13.model.ImcState
import java.text.DecimalFormat

class IMCViewModel : ViewModel() {
    var state by mutableStateOf(ImcState())
        private set


    // Cálculo del IMC
    fun calcularIMC(peso:String, altura : String) : Pair<String,Boolean>{

            var imc:Double = 0.0
            var showAlert:Boolean=false

        try {
            // Code that may throw an exception
            peso.toDouble()
            altura.toDouble()
        }
        catch (e: NumberFormatException) {
            showAlert=true
        }

            if (showAlert==false && altura.toDouble() > 0) {
                imc = (peso.toDouble() /(alturaAcm(altura.toDouble()) * (alturaAcm(altura.toDouble()))))
                val dec = DecimalFormat("#.##")
                var imcString:String
                imcString = dec.format(imc)
                return Pair(imcString,showAlert)
            }
            else{
                return Pair("0.0",showAlert)
            }




    }

    private fun alturaAcm(altura:Double):Double{
        val alturacm =(altura/100)
        return alturacm
    }
}
