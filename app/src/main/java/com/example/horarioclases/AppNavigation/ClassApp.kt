package com.example.horarioclases.AppNavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import com.example.horarioclases.DataBase.FirebaseClaseRepository
import com.example.horarioclases.Screen.ActualClassScreen
import com.example.horarioclases.Screen.AddClassScreen
import com.example.horarioclases.Screen.ViewClassScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassApp() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }
    val repository = FirebaseClaseRepository()

    HorarioClasesTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {  Text(when (currentScreen) {
                        Screen.Home -> "Home"
                        Screen.AddClass -> "Añadir Clase"
                        Screen.ViewClass -> "Consultar Horario"
                        Screen.ActualClass -> "Clase Actual"
                    }) },
                    navigationIcon = {
                        if (currentScreen != Screen.Home) {
                            IconButton(onClick = { currentScreen = Screen.Home }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                            }
                        }
                    }

                )
            }
        ) { padding ->
            when (currentScreen) {
                Screen.Home -> HomeScreen(
                    onAddClassClick = { currentScreen = Screen.AddClass },
                    onViewScheduleClick = { currentScreen = Screen.ViewClass },
                    onCurrentClassClick = { currentScreen = Screen.ActualClass },
                    modifier = Modifier.padding(padding)
                )
                Screen.AddClass -> AddClassScreen(
                    onAddClass = {
                        currentScreen = Screen.Home
                    },
                    modifier = Modifier.padding(padding)
                )
                Screen.ViewClass -> ViewClassScreen(
                    repository = repository,
                    modifier = Modifier.padding(padding)
                )
                Screen.ActualClass -> ActualClassScreen(
                    repository = repository,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

enum class Screen {
    Home,
    AddClass,
    ViewClass,
    ActualClass
}