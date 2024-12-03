package com.example.horarioclases.Screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.horarioclases.DataBase.Clase
import com.example.horarioclases.DataBase.FirebaseClaseRepository

@Composable
fun AddClassScreen(
    onAddClass: (Clase) -> Unit,
    modifier: Modifier = Modifier
) {
    val repository = FirebaseClaseRepository()

    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var profesor by remember { mutableStateOf("") }
    var aula by remember { mutableStateOf("") }
    val diasLaborales = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        TextField(
            value = horario,
            onValueChange = { horario = it },
            label = { Text("Horario") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = dia,
                onValueChange = { dia = it },
                readOnly = true,
                label = { Text("Día") },
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                    }
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                diasLaborales.forEach { diaLaboral ->
                    DropdownMenuItem(
                        text = { Text(diaLaboral) },
                        onClick = {
                            dia = diaLaboral
                            expanded = false
                        }
                    )
                }
            }
        }
        TextField(
            value = profesor,
            onValueChange = { profesor = it },
            label = { Text("Profesor") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        TextField(
            value = aula,
            onValueChange = { aula = it },
            label = { Text("Aula") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        Button(
            onClick = {
                val clase = Clase(id, nombre, horario, dia, profesor, aula)
                Log.d("AddClassScreen", "Adding class: $clase")
                repository.addClase(clase)
                onAddClass(clase)
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Añadir Clase")
        }
    }
}