package com.example.horarioclases.DataBase

data class Clase(
    val id: String? = null,
    val nombre: String,
    val horario: String,
    val dia: String,
    val profesor: String,
    val aula: String
)
