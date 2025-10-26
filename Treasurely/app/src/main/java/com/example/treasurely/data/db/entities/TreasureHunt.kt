package com.example.treasurely.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "treasure_hunts")
data class TreasureHunt(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val description: String? = null,

    // We may wanna set this as a Location object with more detailed data
    val gpsStartingLocation: String,
    val searchRadiusMeters: Int,

    val startTime: LocalDateTime? = LocalDateTime.now(),
    val endTime: LocalDateTime? = LocalDateTime.now().plusHours(2),
    val createdAt: LocalDateTime = LocalDateTime.now(),

    val reward: String? = null,
    val coverImage: ByteArray? = null
)