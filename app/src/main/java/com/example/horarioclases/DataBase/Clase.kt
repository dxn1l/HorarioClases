package com.example.horarioclases.DataBase

data class Clase(
    val id: String? = null,
    val nombre: String = "",
    val horarioInicio: String = "",
    val horarioFinal: String = "",
    val dia: String = "",
    val profesor: String = "",
    val aula: String = ""
)