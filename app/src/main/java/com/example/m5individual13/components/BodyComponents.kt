package com.example.m5individual13.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m5individual13.R

@Composable
fun EspacioNormal(){
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun Titulo(){
    Text(text = stringResource(id = R.string.title),
        color = Color.Black,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        modifier= Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
    )
}

@Composable
fun CustomOutlinedTextField(value: String,
                            onValueChange:(String)-> Unit,
                            label: String
){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBoton(){
    val selectedOption = remember { mutableStateOf<Int?>(null) }
    val options = listOf("Hombre","Mujer")
    MultiChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                icon = {
                    SegmentedButtonDefaults.Icon(active = selectedOption.value ==index) {

                    }
                },
                onCheckedChange = {
                    if (selectedOption.value==index) {
                        selectedOption.value==index
                    } else {
                        selectedOption.value=index
                    }
                },
                checked = selectedOption.value == index
            ) {
                Text(label)
            }
        }
    }
}

@Composable
fun TextoGrande(imc:String){
    Text(text = imc, modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxSize())

}

@Composable
fun Boton(text:String,onClick:()-> Unit){
    Button(onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp)) {
        Text(text)
    }
}

@Composable
fun Texto(text:String){
    Text(text = text ,
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        textAlign = TextAlign.Center,

        color = Color.Black,
        fontSize = 70.sp,
    )
}

@Composable
fun Alert(
    title: String,
    msj: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(text = title) },
        text = { Text(text = msj) },
        shape = CutCornerShape(10.dp),
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(text = confirmText)
            }
        }
    )
}

@Composable
fun AniadirPaciente(
    title: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    value: String,
    onValueChange:(String)-> Unit,
    label: String

) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(text = title) },
        text = { Column {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(text = label) },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            )
        } },
        shape = CutCornerShape(10.dp),
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(text = confirmText)
            }
        }
    )
}

