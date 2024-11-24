package com.example.m5individual13.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.m5individual13.components.Alert
import com.example.m5individual13.components.Boton
import com.example.m5individual13.components.CustomOutlinedTextField
import com.example.m5individual13.components.EspacioNormal
import com.example.m5individual13.components.MultiBoton
import com.example.m5individual13.components.Texto
import com.example.m5individual13.components.Titulo
import com.example.m5individual13.viewmodel.IMCViewModel

@Composable
fun DetailView(viewModel: IMCViewModel, navController: NavHostController) {

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = Color.White
    ) {
        ContentDetailView(it,viewModel)
    }

}

@Composable
fun ContentDetailView(paddingValues: PaddingValues, viewModel: IMCViewModel) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        var edad:String by remember { mutableStateOf("") }
        var altura by remember { mutableStateOf("") }
        var peso by remember { mutableStateOf("") }
        var imc: String by remember { mutableStateOf("0.0") }
        var showAlert: Boolean by remember{ mutableStateOf(false) }

        EspacioNormal()
        MultiBoton()
        EspacioNormal()
        Titulo()
        EspacioNormal()
        CustomOutlinedTextField( edad,{edad=it},"Edad")
        CustomOutlinedTextField(peso,{peso=it},"Peso (kg)")
        CustomOutlinedTextField(altura,{altura=it},"Altura (cm)")
        EspacioNormal()
        Boton(text = "Calcular"){
            val result = viewModel.calcularIMC(peso, altura)
            imc = result.first
            showAlert = result.second

        }
        EspacioNormal()
        Texto(imc)

        if (showAlert) {
            Alert(
                title = "Alerta",
                msj = "Ingresa Peso y Altura válidos",
                confirmText = "Aceptar",
                onConfirmClick = { showAlert = false }
            ) { }
        }
    }
}