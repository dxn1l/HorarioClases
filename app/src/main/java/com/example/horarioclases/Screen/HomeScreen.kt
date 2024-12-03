package com.example.horarioclases.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.horarioclases.ui.theme.CustomButtonDefaults

@Composable
fun HomeScreen(
    onAddClassClick: () -> Unit,
    onViewScheduleClick: () -> Unit,
    onCurrentClassClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onAddClassClick,
            colors = CustomButtonDefaults.buttonColors,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Añadir Clase")
        }
        Button(
            onClick = onViewScheduleClick,
            colors = CustomButtonDefaults.buttonColors,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Consultar Horario")
        }
        Button(
            onClick = onCurrentClassClick,
            colors = CustomButtonDefaults.buttonColors,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Clase Actual")
        }
    }
}