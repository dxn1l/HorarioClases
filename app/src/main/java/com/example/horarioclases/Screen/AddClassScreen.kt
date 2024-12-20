package com.example.horarioclases.Screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddClassScreen(
    onAddClass: (Clase) -> Unit,
    modifier: Modifier = Modifier
) {
    val repository = FirebaseClaseRepository()

    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var horarioInicio by remember { mutableStateOf("") }
    var horarioFinal by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var profesor by remember { mutableStateOf("") }
    var aula by remember { mutableStateOf("") }
    val diasLaborales = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    var expanded by remember { mutableStateOf(false) }

    var nombreError by remember { mutableStateOf(false) }
    var horarioInicioError by remember { mutableStateOf(false) }
    var horarioFinalError by remember { mutableStateOf(false) }
    var diaError by remember { mutableStateOf(false) }
    var profesorError by remember { mutableStateOf(false) }
    var formError by remember { mutableStateOf(false) }

    fun isValidTime(time: String): Boolean {
        return time.matches(Regex("^(?:[01]\\d|2[0-3]):[0-5]\\d$"))
    }

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
            onValueChange = {
                nombre = it
                nombreError = it.any { char -> char.isDigit() }
            },
            label = { Text("Nombre") },
            isError = nombreError,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        if (nombreError) {
            Text(
                text = "El nombre no puede contener números",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        TextField(
            value = horarioInicio,
            onValueChange = {
                horarioInicio = it
                horarioInicioError = !isValidTime(it)
            },
            label = { Text("Horario Inicio (HH:MM)") },
            isError = horarioInicioError,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        if (horarioInicioError) {
            Text(
                text = "El horario debe estar entre 00:00 y 23:59",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        TextField(
            value = horarioFinal,
            onValueChange = {
                horarioFinal = it
                horarioFinalError = !isValidTime(it)
            },
            label = { Text("Horario Final (HH:MM)") },
            isError = horarioFinalError,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        if (horarioFinalError) {
            Text(
                text = "El horario debe estar entre 00:00 y 23:59",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = dia,
                onValueChange = { dia = it },
                readOnly = true,
                label = { Text("Día") },
                isError = diaError,
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
                            diaError = false
                        }
                    )
                }
            }
        }
        if (diaError) {
            Text(
                text = "Debe seleccionar un día",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        TextField(
            value = profesor,
            onValueChange = {
                profesor = it
                profesorError = it.any { char -> char.isDigit() }
            },
            label = { Text("Profesor") },
            isError = profesorError,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        if (profesorError) {
            Text(
                text = "El nombre del profesor no puede contener números",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        TextField(
            value = aula,
            onValueChange = { aula = it },
            label = { Text("Aula") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        Button(
            onClick = {
                formError = id.isBlank() || nombre.isBlank() || horarioInicio.isBlank() || horarioFinal.isBlank() || dia.isBlank() || profesor.isBlank() || aula.isBlank() || nombreError || horarioInicioError || horarioFinalError || diaError || profesorError
                if (!formError) {
                    val clase = Clase(
                        id = id,
                        nombre = nombre,
                        horarioInicio = horarioInicio,
                        horarioFinal = horarioFinal,
                        dia = dia,
                        profesor = profesor,
                        aula = aula
                    )
                    Log.d("AddClassScreen", "Adding class: $clase")
                    repository.addClase(clase)
                    onAddClass(clase)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Añadir Clase")
        }
        if (formError) {
            Text(
                text = "Por favor, complete todos los campos correctamente",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}