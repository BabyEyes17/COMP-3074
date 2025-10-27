package com.example.treasurely.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(

    tableName = "clues",

    foreignKeys = [

        // Link to corresponding Treasure Hunt
        ForeignKey(
            entity = TreasureHunt::class,
            parentColumns = ["id"],
            childColumns = ["treasureHuntId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index("treasureHuntId")
    ]
)

data class Clue(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val treasureHuntId: Long,

    val name: String,
    val description: String? = null,

    // We may wanna set this as a Location object with more detailed data
    val gpsLocation: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val pointsReward: Int? = 0,
    val photo: ByteArray? = null,

    val nextClueId: Long? = null,
    val nextClueHint: String? = null,
    val displayNextClueImage: Boolean? = false
)
