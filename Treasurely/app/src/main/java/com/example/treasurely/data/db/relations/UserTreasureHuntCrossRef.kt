//package com.example.treasurely.data.db.relations
//
//import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.Index
//import com.example.treasurely.data.db.entities.User
//import com.example.treasurely.data.db.entities.TreasureHunt
//
//
//@Entity(
//    tableName = "user_treasure_hunt_join",
//    primaryKeys = ["userId", "huntId"],
//    foreignKeys = [
//        ForeignKey(
//            entity = User::class,
//            parentColumns = ["id"],
//            childColumns = ["userId"],
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = TreasureHunt::class,
//            parentColumns = ["id"],
//            childColumns = ["huntId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ],
//    indices = [
//        Index(value = ["userId"]),
//        Index(value = ["huntId"])
//    ]
//)
//data class UserTreasureHuntCrossRef(
//    val userId: Long,
//    val huntId: Long
//)
