package com.example.horarioclases.Screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.horarioclases.DataBase.Clase
import com.example.horarioclases.DataBase.FirebaseClaseRepository
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ActualClassScreen(
    repository: FirebaseClaseRepository,
    modifier: Modifier = Modifier
) {
    val diasLaborales = listOf("lunes", "martes", "miércoles", "jueves", "viernes")
    val currentDateTime = LocalDateTime.now()
    val currentDay = currentDateTime.format(DateTimeFormatter.ofPattern("EEEE", Locale("es", "ES"))).lowercase()
    val currentTime = currentDateTime.toLocalTime()
    var currentClass by remember { mutableStateOf<Clase?>(null) }

    LaunchedEffect(currentDay, currentTime) {
        Log.d("ActualClassScreen", "LaunchedEffect triggered with currentDay: $currentDay and currentTime: $currentTime")
        repository.getClases(
            onResult = { allClases ->
                currentClass = allClases.find { clase ->
                    clase.dia.lowercase() == currentDay &&
                            LocalTime.parse(clase.horarioInicio) <= currentTime &&
                            LocalTime.parse(clase.horarioFinal) >= currentTime
                }
                Log.d("ActualClassScreen", "Current class found: $currentClass")
            },
            onError = { error ->
                Log.e("ActualClassScreen", "Error fetching classes: $error")
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentClass != null) {
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
                        text = currentClass!!.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Horario: ${currentClass!!.horarioInicio} - ${currentClass!!.horarioFinal}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Profesor: ${currentClass!!.profesor}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Aula: ${currentClass!!.aula}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else {
            Text(
                text = "No hay clases en este momento",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}