package com.example.treasurely.data.db.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.db.entities.Clue

@Entity(
    tableName = "user_clue_progress_join",
    primaryKeys = ["userId", "clueId"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Clue::class,
            parentColumns = ["id"],
            childColumns = ["clueId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("userId"),
        Index("clueId")
    ]
)
data class UserClueProgressCrossRef(
    val userId: Long,
    val clueId: Long,
    val discoveredAt: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)
