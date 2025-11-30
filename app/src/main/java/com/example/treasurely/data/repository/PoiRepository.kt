package com.example.treasurely.data.repository

import com.example.treasurely.data.dao.PoiDao
import com.example.treasurely.data.model.PointOfInterest

class PoiRepository(private  val dao: PoiDao) {



    /* POST */
    suspend fun createPoi(poi: PointOfInterest) = dao.createPoi(poi)



    /* GET */
    fun getAllPoi() = dao.getAllPoi()

    fun getPoiById(id: String) = dao.getPoiById(id)

    fun getPoiByName(name: String) = dao.getPoiByName(name)

    fun searchByNameOrTag(q: String) = dao.searchByNameOrTag(q)



    /* PUT */
    suspend fun updatePoi(poi: PointOfInterest) = dao.updatePoi(poi)



    /* DELETE */
    suspend fun deletePoi(poi: PointOfInterest) = dao.deletePoi(poi)
}
