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

    @Query("SELECT * FROM points_of_interest ORDER BY createdAt DESC")
    fun getAllPoi(): LiveData<List<PointOfInterest>>

    @Query("SELECT * FROM points_of_interest WHERE id = :id LIMIT 1")
    fun getPoiById(id: String): LiveData<PointOfInterest?>

    @Query("SELECT * FROM points_of_interest WHERE name LIKE '%' || :name || '%' ORDER BY createdAt DESC")
    fun getPoiByName(name: String): LiveData<List<PointOfInterest>>

    // (5) Search by name OR tags
    @Query("""
    SELECT * FROM points_of_interest
    WHERE LOWER(name) LIKE '%' || LOWER(:q) || '%'
       OR LOWER(tags) LIKE '%' || LOWER(:q) || '%'
    ORDER BY createdAt DESC
""")
    fun searchByNameOrTag(q: String): LiveData<List<PointOfInterest>>


    /* PUT */
    @Update
    suspend fun updatePoi(poi: PointOfInterest)



    /* DELETE */
    @Delete
    suspend fun deletePoi(poi: PointOfInterest)
}
