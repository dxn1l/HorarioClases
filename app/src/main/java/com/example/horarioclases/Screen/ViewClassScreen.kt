package com.example.horarioclases.Screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.horarioclases.DataBase.Clase
import com.example.horarioclases.DataBase.FirebaseClaseRepository

@Composable
fun ViewClassScreen(
    repository: FirebaseClaseRepository,
    modifier: Modifier = Modifier
) {
    val diasLaborales = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    var selectedDia by remember { mutableStateOf(diasLaborales.first()) }
    var expanded by remember { mutableStateOf(false) }
    var clases by remember { mutableStateOf(listOf<Clase>()) }

    LaunchedEffect(selectedDia) {
        repository.getClases(
            onResult = { allClases ->
                clases = allClases.filter { it.dia == selectedDia }
            },
            onError = { error ->
                Log.e("ViewClassScreen", "Error fetching classes: $error")
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = selectedDia,
                onValueChange = { selectedDia = it },
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
                            selectedDia = diaLaboral
                            expanded = false
                        }
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(clases) { clase ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = clase.nombre,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Horario: ${clase.horarioInicio} - ${clase.horarioFinal}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Profesor: ${clase.profesor}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Aula: ${clase.aula}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        IconButton(
                            onClick = {
                                repository.removeClase(clase.id ?: "")
                                clases = clases.filter { it.id != clase.id }
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete Class")
                        }
                    }
                }
            }
        }
    }
}