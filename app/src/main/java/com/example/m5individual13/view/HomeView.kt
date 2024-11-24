package com.example.m5individual13.view

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.m5individual13.DataStoreManager
import com.example.m5individual13.R
import com.example.m5individual13.components.Alert
import com.example.m5individual13.components.AniadirPaciente
import com.example.m5individual13.ui.theme.Purple80
import com.example.m5individual13.viewmodel.IMCViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: IMCViewModel, navController: NavHostController,context: Context) {
    var cardsList by remember { mutableStateOf(listOf<String>()) }
    var showAlert by remember { mutableStateOf(false) }

    // Cargar las tarjetas guardadas al inicio
    LaunchedEffect(Unit) {
        DataStoreManager.getCards(context).collect { savedCards ->
            cardsList = savedCards.toList()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAlert = true },
                containerColor = Purple80
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir paciente")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(cardsList.size) { index ->
                    CardItem(content = cardsList[index], navController)
                }
            }
            if (showAlert) {
                CrearCard(
                    onDismiss = { showAlert = false },
                    onConfirm = { newName ->
                        if (newName.isNotBlank()) {
                            val updatedList = cardsList + newName
                            cardsList = updatedList
                            // Guardar las tarjetas en DataStore
                            CoroutineScope(Dispatchers.IO).launch {
                                DataStoreManager.saveCards(context, updatedList.toSet())
                            }
                            showAlert = false
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CardItem(content: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = content,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("DetailView") }) {
                Text("Calcular IMC")
            }
        }
    }
}

@Composable
fun CrearCard(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var nombre by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Ingresa Paciente")
        },
        text = {
            Column {
                Text(text = "Escribe el nombre del paciente:")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(nombre) }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}
