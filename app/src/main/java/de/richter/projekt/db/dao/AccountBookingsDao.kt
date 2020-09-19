package de.richter.projekt.db.dao

import androidx.room.*
import de.richter.projekt.db.entity.AccountBookingsEntity

@Dao
interface AccountBookingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: AccountBookingsEntity)

    @Delete
    fun delete(data: AccountBookingsEntity)

    @Query("SELECT * FROM account_bookings")
    fun getAllAccountBookings(): List<AccountBookingsEntity>
}