package com.example.treasurely.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class User(

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    val name: String,
    val email: String,
    val phone: String
)
