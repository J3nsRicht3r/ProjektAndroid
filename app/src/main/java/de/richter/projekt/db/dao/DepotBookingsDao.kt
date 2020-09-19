package de.richter.projekt.db.dao

import androidx.room.*
import de.richter.projekt.db.entity.DepotBookingsEntity

@Dao
interface DepotBookingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DepotBookingsEntity)

    @Delete
    fun delete(data: DepotBookingsEntity)

    @Query("SELECT * FROM depot_bookings")
    fun getAllDepotBookings(): List<DepotBookingsEntity>
}