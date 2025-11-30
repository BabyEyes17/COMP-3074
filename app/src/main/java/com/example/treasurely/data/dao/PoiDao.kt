package com.example.treasurely.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.treasurely.data.model.PointOfInterest

@Dao
interface PoiDao {



    /* POST */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPoi(poi: PointOfInterest)



    /* GET */
    @Query("SELECT * FROM points_of_interest")
    fun getAllPoi(): LiveData<List<PointOfInterest>>

    @Query("SELECT * FROM points_of_interest WHERE id = :id")
    fun getPoiById(id: String): LiveData<PointOfInterest>

    @Query("SELECT * FROM points_of_interest WHERE name LIKE '%' || :name || '%'")
    fun getPoiByName(name: String): LiveData<List<PointOfInterest>>



    /* PUT */
    @Update
    suspend fun updatePoi(poi: PointOfInterest)



    /* DELETE */
    @Delete
    suspend fun deletePoi(poi: PointOfInterest)
}
