package de.richter.projekt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.richter.projekt.db.dao.AccountBookingsDao
import de.richter.projekt.db.entity.AccountBookingsEntity
import de.richter.projekt.db.entity.DateTimeConverter

@Database(entities = arrayOf(AccountBookingsEntity::class), version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class AccountBookingsDatabase : RoomDatabase() {
    abstract fun accountBookingsDao(): AccountBookingsDao

    object Factory {
        private var instance: AccountBookingsDatabase? = null

        fun getInstance(context: Context): AccountBookingsDatabase {
            if (instance == null) {
                synchronized(AccountBookingsDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AccountBookingsDatabase::class.java,
                            "account_bookings"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance!!
        }
    }
}