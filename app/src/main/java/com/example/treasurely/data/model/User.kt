package com.example.treasurely.data.model

import androidx.room.PrimaryKey
import java.util.UUID

data class User(

    @PrimaryKey(autoGenerate = true)
    val id: UUID,
    val name: String,
    val email: String,
    val phone: String
)
