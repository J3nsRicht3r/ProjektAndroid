package de.richter.projekt.db.entity

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateTimeConverter {

    private val formatDateTimeString = "yyyy-MM-dd'T'HH:mm:ss"

    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    fun toLocalDateTime(value: String): Date {
        val sdf = SimpleDateFormat(formatDateTimeString)
        sdf.timeZone = TimeZone.getDefault()
        val localDate: Date = sdf.parse(value)!!
        return localDate
    }
}