package com.example.rickandmortyseries.common

fun String.translateToSpecie(): String =
    when (this) {
        "Human" -> "Humano"
        "Alien" ->  "Alienígena"
        else ->  "Desconhecido"
    }


fun String.translateToOrigin(): String =
    when (this) {
        "Earth" -> "Terra"
        "Earth (Replacement Dimension)" -> "Terra (Dimensão de substituição)"
        "unknown" -> "Desconhecido"
        else -> "Desconhecido"
    }

fun String.translateToStatus(): String =
    when (this) {
        "Alive" -> "Vivo"
        "Dead" -> "Morto"
        "unknown" -> "Desconhecido"
        else -> "Desconhecido"
    }

fun String.translateToGender(): String =
    when (this) {
        "Female" -> "Feminino"
        "Male" -> "Masculino"
        "Genderless" -> "Sem gênero"
        "unknown" -> "Desconhecido"
        else -> "Desconhecido"
    }
