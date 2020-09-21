package de.richter.projekt.db.repository

import android.app.Application
import de.richter.projekt.db.CurrenciesAccountBookingsDatabase
import de.richter.projekt.db.dao.CurrenciesAccountBookingsDao
import de.richter.projekt.db.entity.CurrenciesAccountBookingsEntity

class CurrenciesAccountBookingsRepository(app: Application) {
    private var currenciesAccountBookingsDao: CurrenciesAccountBookingsDao
    private val allCurrenciesAccountBookings: List<CurrenciesAccountBookingsEntity>

    init {
        val database = CurrenciesAccountBookingsDatabase.Factory.getInstance(app.applicationContext)
        currenciesAccountBookingsDao = database.currenciesAccountBookingsDao()
        allCurrenciesAccountBookings =
            currenciesAccountBookingsDao.getAllCurrenciesAccountBookings()
    }

    suspend fun insert(data: CurrenciesAccountBookingsEntity) {
        currenciesAccountBookingsDao.insert(data)
    }

    fun delete(data: CurrenciesAccountBookingsEntity) {
        currenciesAccountBookingsDao.delete(data)
    }

    fun getAllCurrenciesAccountBookings(): List<CurrenciesAccountBookingsEntity> {
        return currenciesAccountBookingsDao.getAllCurrenciesAccountBookings()
    }
}