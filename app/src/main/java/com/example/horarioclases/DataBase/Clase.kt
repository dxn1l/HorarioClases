package com.example.horarioclases.DataBase

import java.time.LocalTime

data class Clase(
    val id: String? = null,
    val nombre: String,
    val horarioInicio: LocalTime,
    val horarioFinal: LocalTime,
    val dia: String,
    val profesor: String,
    val aula: String
)
