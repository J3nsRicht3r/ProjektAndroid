package de.richter.projekt.db.import

import android.content.Context
import java.io.IOException
import java.io.InputStream

class CurrenciesExchangeRateImportJson(val context: Context) {

    fun importJson(): String {
        val fileName = "exchange_rate.json"
        var input: InputStream? = null
        var jsonString: String?
        try {
            input = context.assets.open(fileName)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            jsonString = String(buffer)

            return jsonString

        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            input?.close()
        }
        return ""
    }
//	fun writeListFromJson(): List<CurrenciesExchangeRateEntity>{
//		val list: List<CurrenciesExchangeRateEntity>
//		list =
//		return list
//	}

}