package de.richter.projekt.db.dao

import androidx.room.*
import de.richter.projekt.db.entity.CurrenciesAccountBookingsEntity

@Dao
interface CurrenciesAccountBookingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: CurrenciesAccountBookingsEntity)

    @Delete
    fun delete(data: CurrenciesAccountBookingsEntity)

    @Query("SELECT * FROM currencies_account_bookings")
    fun getAllCurrenciesAccountBookings(): List<CurrenciesAccountBookingsEntity>
}