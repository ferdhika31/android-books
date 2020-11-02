package id.web.dika.mybook.utils

import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun rupiah(number: Double): String{
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(number).toString()
}

fun formatDate(date: String, format: String): String {
    var formattedDate = ""
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")

    try {
        val parseDate = sdf.parse(date)
        formattedDate = SimpleDateFormat(format).format(parseDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return formattedDate
}