package com.example.horarioclases.AppNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.horarioclases.Screen.HomeScreen
import com.example.horarioclases.ui.theme.HorarioClasesTheme
import androidx.compose.ui.Modifier
import com.example.horarioclases.Screen.AddClassScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassApp() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }

    HorarioClasesTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {  Text(when (currentScreen) {
                        Screen.Home -> "Home"
                        Screen.AddClass -> "Añadir Clase"
                    }) }

                )
            }
        ) { padding ->
            when (currentScreen) {
                Screen.Home -> HomeScreen(
                    onAddClassClick = { currentScreen = Screen.AddClass },
                    onViewScheduleClick = { },
                    onCurrentClassClick = { },
                    modifier = Modifier.padding(padding)
                )
                Screen.AddClass -> AddClassScreen(
                    onAddClass = {
                        currentScreen = Screen.Home
                    },
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

enum class Screen {
    Home,
    AddClass
}