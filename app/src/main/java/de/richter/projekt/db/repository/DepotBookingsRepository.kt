package de.richter.projekt.db.repository

import android.app.Application
import de.richter.projekt.db.DepotBookingsDatabase
import de.richter.projekt.db.dao.DepotBookingsDao
import de.richter.projekt.db.entity.DepotBookingsEntity

class DepotBookingsRepository(app: Application) {
    private var depotBookingsDao: DepotBookingsDao
    private val allDepotBookings: List<DepotBookingsEntity>

    init {
        val database = DepotBookingsDatabase.Factory.getInstance(app.applicationContext)
        depotBookingsDao = database.depotBookingsDao()
        allDepotBookings = depotBookingsDao.getAllDepotBookings()
    }

    suspend fun insert(data: DepotBookingsEntity) {
        depotBookingsDao.insert(data)
    }

    fun delete(data: DepotBookingsEntity) {
        depotBookingsDao.delete(data)
    }

    suspend fun getAllDepotBookings(): List<DepotBookingsEntity> {
        return depotBookingsDao.getAllDepotBookings()
    }
}