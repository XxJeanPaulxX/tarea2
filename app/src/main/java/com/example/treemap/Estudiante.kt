package com.example.treemap

data class Estudiante(
    val nombre: String,
    val pp1: Int,
    val pp2: Int,
    val pp3: Int
) {
    val promedio: Double
        get() = (pp1 + pp2 + pp3) / 3.0

    val estado: String
        get() = when {
            promedio >= 13 -> "Aprobado"
            promedio >= 10 -> "Sustitutorio"
            else -> "Desaprobado"
        }
}
