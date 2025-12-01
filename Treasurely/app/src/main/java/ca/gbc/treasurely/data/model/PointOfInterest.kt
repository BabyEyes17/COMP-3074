package ca.gbc.treasurely.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "points_of_interest")
data class PointOfInterest(

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    val name: String,
    val address: String,
    val task: String,

    val tags: List<String>,
    val rating: Int? = null,

    val latitude: Double,
    val longitude: Double,

    val qrCodeValue: String,
    val isFound: Boolean = false,

    val createdAt: Long = System.currentTimeMillis()
)
