package de.richter.projekt.db.repository

import android.app.Application
import de.richter.projekt.db.AccountBookingsDatabase
import de.richter.projekt.db.dao.AccountBookingsDao
import de.richter.projekt.db.entity.AccountBookingsEntity

class AccountBookingsRepository(app: Application) {
    private var accountBookingsDao: AccountBookingsDao
    private val allAccountBookings: List<AccountBookingsEntity>

    init {
        val database = AccountBookingsDatabase.Factory.getInstance(app.applicationContext)
        accountBookingsDao = database.accountBookingsDao()
        allAccountBookings = accountBookingsDao.getAllAccountBookings()
    }

    suspend fun insert(data: AccountBookingsEntity) {
        accountBookingsDao.insert(data)
    }

    fun delete(data: AccountBookingsEntity) {
        accountBookingsDao.delete(data)
    }

    fun getAllAccountBookings(): List<AccountBookingsEntity> {
        return accountBookingsDao.getAllAccountBookings()
    }
}