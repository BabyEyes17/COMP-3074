package com.example.treasurely.data.db.relations

import androidx.room.Entity

@Entity(
    tableName = "user_treasure_hunt_join",
    primaryKeys = ["userId", "huntId"]
)
data class UserTreasureHuntCrossRef(
    val userId: Long,
    val huntId: Long
)