package de.richter.projekt.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import de.richter.projekt.db.CurrenciesExchangeRateDatabase
import de.richter.projekt.db.dao.CurrenciesExchangeRateDao
import de.richter.projekt.db.entity.CurrenciesExchangeRateEntity

class CurrenciesExchangeRateRepository(app: Application) {
    private var currenciesExchangeRateDao: CurrenciesExchangeRateDao
    private val allCurrenciesExchangeRates: LiveData<List<CurrenciesExchangeRateEntity>>
//    private val target: List<TargetExchangeRate>

    init {
        val database = CurrenciesExchangeRateDatabase.Factory.getInstance(app.applicationContext)
        currenciesExchangeRateDao = database.currenciesExchangeRateDao()
        allCurrenciesExchangeRates = currenciesExchangeRateDao.getAllCurrenciesExchangeRates()
//        target = currenciesExchangeRateDao.getTarget()
    }

    suspend fun insert(data: CurrenciesExchangeRateEntity) {
        currenciesExchangeRateDao.insert(data)
    }

    fun delete(data: CurrenciesExchangeRateEntity) {
        currenciesExchangeRateDao.delete(data)
    }

    fun getAllCurrenciesExchangeRates(): LiveData<List<CurrenciesExchangeRateEntity>> {
        return allCurrenciesExchangeRates
    }

    fun getAll(): List<CurrenciesExchangeRateEntity> {
        return currenciesExchangeRateDao.getAll()
    }
//    fun getTarget(target: String):List<TargetExchangeRate{
//        return target
//    }
}