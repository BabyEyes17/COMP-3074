package com.example.treasurely.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val username: String,
    val email: String,

    val passwordHash: String,
    val salt: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val totalPoints: Int = 0,
    val profilePhoto: ByteArray? = null
)
