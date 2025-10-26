package com.example.treasurely.data


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue



data class Hunt(
    val id: String,          // use the 6-digit code as the ID for now
    val name: String,
    val totalClues: Int,
    var found: Int,
    var points: Int
)

object AppState {
    val myHunts = mutableStateListOf<Hunt>()
    var totalCluesFound by mutableIntStateOf(0); private set
    var totalPoints by mutableIntStateOf(0); private set

    private val sixDigits = Regex("^\\d{6}$")

    fun join(code: String): Result<Hunt> {
        val key = code.trim()
        if (!sixDigits.matches(key)) {
            return Result.failure(IllegalArgumentException("Code must be 6 digits"))
        }
        if (myHunts.any { it.id == key }) {
            return Result.failure(IllegalStateException("Already joined"))
        }
        // Placeholder hunt (no data). You can replace these fields later.
        val hunt = Hunt(
            id = key,
            name = "Hunt $key",
            totalClues = 5,
            found = 0,
            points = 500
        )
        myHunts += hunt
        recompute()
        return Result.success(hunt)
    }

    fun addFoundClue(huntId: String) {
        val i = myHunts.indexOfFirst { it.id == huntId }
        if (i >= 0) {
            val h = myHunts[i]
            if (h.found < h.totalClues) {
                h.found += 1
                totalCluesFound += 1
            }
        }
        recompute()
    }

    private fun recompute() {
        totalCluesFound = myHunts.sumOf { it.found }
        totalPoints = myHunts.sumOf { it.points }
    }
}
