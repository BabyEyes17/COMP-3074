package com.example.treasurely.data.db.utilities

object TreasureHuntJoinCodeGenerator {

    private val chars = ('A'..'Z') + ('0'..'9')

    fun generateJoinCode(length: Int = 6): String {

        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}