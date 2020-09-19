package de.richter.projekt.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.richter.projekt.db.entity.CurrenciesExchangeRateEntity

@Dao
interface CurrenciesExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: CurrenciesExchangeRateEntity)

    @Delete
    fun delete(data: CurrenciesExchangeRateEntity)

    // return all CurranciesExchacheRates using LiveData
    @Query("SELECT * FROM currencies_exchange_rate")
    fun getAllCurrenciesExchangeRates(): LiveData<List<CurrenciesExchangeRateEntity>>
//    // Returning a subset of Columns from a Room database query
//    @Query("SELECT target, rate FROM currencies_exchange_rate")
//    fun getTargetWithRate():List<TargetExchangeRate>
    // Returning a Parameter when querying a Room database
//    @Query("SELECT * FROM currencies_exchange_rate WHERE target = :target")
//    fun getTarget(target: String): LiveData<List<CurrenciesExchangeRate>>

    @Query("SELECT * FROM currencies_exchange_rate")
    fun getAll(): List<CurrenciesExchangeRateEntity>
}