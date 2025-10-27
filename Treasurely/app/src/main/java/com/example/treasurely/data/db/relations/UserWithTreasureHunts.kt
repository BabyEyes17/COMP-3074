package com.example.treasurely.data.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.treasurely.data.db.entities.TreasureHunt
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.db.relations.UserTreasureHuntCrossRef

data class UserWithTreasureHunts(

    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(UserTreasureHuntCrossRef::class)
    )

    val treasureHunts: List<TreasureHunt>
)
