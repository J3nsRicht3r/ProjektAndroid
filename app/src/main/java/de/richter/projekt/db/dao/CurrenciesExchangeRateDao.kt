package de.richter.projekt.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.richter.projekt.db.data.CurrenciesExchangeRate
import java.math.BigDecimal

@Dao
interface CurrenciesExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: CurrenciesExchangeRate)

    @Delete
    fun delete(data: CurrenciesExchangeRate)

    @Query("SELECT * FROM currenciesExchangeRate")
    fun getAllCurrenciesExchangeRates(): LiveData<List<CurrenciesExchangeRate>>


    @Query("SELECT * FROM currenciesExchangeRate WHERE rate = :rate")
    fun getExchangeRate(rate: BigDecimal)
}