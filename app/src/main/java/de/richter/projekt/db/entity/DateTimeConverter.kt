package de.richter.projekt.db.entity

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateTimeConverter {

    private val formatDateTimeString = "yyyy-MM-dd'T'HH:mm:ssZZZZZ"

    @TypeConverter
    fun fromLocalDateTimeToUTCString(value: Date): String {
        val sdf = SimpleDateFormat(formatDateTimeString)
        val localDateTimeString = sdf.format(value)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val dateTimeUTC: Date = sdf.parse(localDateTimeString)!!
        val dateTimeUTCAsString = sdf.format(dateTimeUTC)
        //Log.d(TAG, "fromLocalDateTimeToUTCString: $value - $dateTimeUTC - $dateTimeUTCAsString")
        return dateTimeUTCAsString
    }

    @TypeConverter
    fun toLocalDateTime(value: String): Date {
        val sdf = SimpleDateFormat(formatDateTimeString)
        sdf.timeZone = TimeZone.getDefault()
        val localDate: Date = sdf.parse(value)!!
        //Log.d(TAG, "toLocalDateTime: $value - $localDate")
        return localDate
    }
}