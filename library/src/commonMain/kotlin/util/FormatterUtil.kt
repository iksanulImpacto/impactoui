package com.impacto.impactoui.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.round
import kotlin.math.pow
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


object FormatterUtil {

    const val PATTERN_TIME_AND_DATE = "HH:mm:ss - dd/MM/yyyy"
    const val PATTERN_DATE_TIME = "dd/MM/yyyy HH:mm:ss"
    const val PATTERN_DATE_TIME_SIMPLE = "dd-MM-yyyy HH:mm"
    const val PATTERN_DATE_TIME_SIMPLE_WITH_STRIP = "dd/MM/yyyy - HH:mm"
    const val PATTER_HOUR_MINUTE = "HH:mm"

    const val DATE_PATTERN_TO_SERVER_SHORT = "yyyy-MM-dd"
    const val DATE_PATTERN_FROM_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_PATTERN_FROM_SERVER_WITHOUT_MS = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val DATE_PATTERN_FROM_SERVER_SHORT = "yyyy-MM-dd"

    val SHORT_MONTHS = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "Mei", "Jun",
        "Agu", "Sep", "Okt", "Nov", "Des"
    )

    val FULL_MONTHS = arrayOf(
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    )

    // --- Date utilities ---
    @OptIn(ExperimentalTime::class)
    fun getTodayDate(): LocalDateTime {
        val now = kotlin.time.Clock.System.now()
        val zone = TimeZone.currentSystemDefault()
        return now.toLocalDateTime(zone)
    }

    @OptIn(ExperimentalTime::class)
    fun Instant.toLocalDateTime(): LocalDateTime =
        this.toLocalDateTime(TimeZone.currentSystemDefault())

    @OptIn(ExperimentalTime::class)
    fun parseServerDateTime(dateString: String?): LocalDateTime? {
        if (dateString.isNullOrBlank()) return null
        return try {
            Instant.parse(dateString).toLocalDateTime(TimeZone.currentSystemDefault())
        } catch (e: Exception) {
            null
        }
    }

    val tz = TimeZone.of("+07:00")

    @OptIn(ExperimentalTime::class)
    fun toIsoString(date: Any?): String {
        return when (date) {
            is LocalDateTime -> date.toInstant(tz).toString()
            is LocalDate -> LocalDateTime(date.year, date.month, date.day, 0, 0)
                .toInstant(tz).toString()
            else -> ""
        }
    }

    fun formatServerDateTime(dateString: String?, pattern: (LocalDateTime) -> String): String {
        val dateTime = parseServerDateTime(dateString) ?: return "-"
        return pattern(dateTime)
    }

    fun dateToFullDateStringWithTime(dateTime: LocalDateTime): String {
        val day = dateTime.day
        val month = FULL_MONTHS[dateTime.month.number - 1]
        val year = dateTime.year
        val hour = dateTime.hour.toString().padStart(2, '0')
        val minute = dateTime.minute.toString().padStart(2, '0')
        return "$day $month $year - $hour:$minute"
    }

    fun dateToFullDateString(dateTime: LocalDateTime): String {
        val day = dateTime.day
        val month = FULL_MONTHS[dateTime.month.number - 1]
        val year = dateTime.year
        return "$day $month $year"
    }

    fun dateToShortStringWithTime(dateTime: LocalDateTime): String {
        val day = dateTime.day
        val month = SHORT_MONTHS[dateTime.month.number - 1]
        val year = dateTime.year
        val hour = dateTime.hour.toString().padStart(2, '0')
        val minute = dateTime.minute.toString().padStart(2, '0')
        return "$day $month $year $hour:$minute"
    }

    fun dateToHourMinute(dateTime: LocalDateTime): String {
        val hour = dateTime.hour.toString().padStart(2, '0')
        val minute = dateTime.minute.toString().padStart(2, '0')
        return "$hour:$minute"
    }

    fun dateToHourMinuteSecond(dateTime: LocalDateTime): String {
        val hour = dateTime.hour.toString().padStart(2, '0')
        val minute = dateTime.minute.toString().padStart(2, '0')
        val second = dateTime.second.toString().padStart(2, '0')
        return "$hour:$minute:$second"
    }

    fun dateToShortString(dateTime: LocalDateTime): String {
        val day = dateTime.day
        val month = SHORT_MONTHS[dateTime.month.number - 1]
        val year = dateTime.year
        return "$day $month $year"
    }

    fun dateToMonthYearString(dateTime: LocalDateTime): String {
        val month = SHORT_MONTHS[dateTime.month.number - 1]
        return "$month ${dateTime.year}"
    }

    fun dateToDayMonthString(dateTime: LocalDateTime): String {
        val day = dateTime.day
        val month = SHORT_MONTHS[dateTime.month.number - 1]
        return "$day $month"
    }

    // --- Number utilities (KMP safe) ---

    /** Format double with 2 decimals and thousand separator */
    fun toSeparateDecimalNumber(value: Double? = 0.0): String =
        formatWithSeparator(value ?: 0.0, decimals = 2)

    /** Format double with 2 decimals (no grouping) */
    fun toDecimalString(value: Double? = 0.0): String =
        (round((value ?: 0.0) * 100) / 100).toString()

    /** Format double as integer with thousand separator */
    fun doubleToSeparateNumber(value: Double? = 0.0): String =
        formatWithSeparator(value ?: 0.0, decimals = 0)

    /** Format long with thousand separator */
    fun longToSeparateNumber(value: Long? = 0L): String =
        formatWithSeparator((value ?: 0L).toDouble(), decimals = 0)

    fun stringToLong(textCurrency: String): Long {
        val value = textCurrency.replace("[^\\d-]".toRegex(), "")
        return value.toLongOrNull() ?: 0L
    }

    fun stringToDouble(stringNumber: String): Double {
        val value = stringNumber.replace("[^\\d.-]".toRegex(), "")
        return value.toDoubleOrNull() ?: 0.0
    }

    fun currencyToDouble(textCurrency: String): Double {
        val value = textCurrency.replace("[^\\d,-]".toRegex(), "").replace(",", ".")
        return value.toDoubleOrNull() ?: 0.0
    }

    fun doubleToDoublePrecision(value: Double): Double =
        round(value * 100) / 100.0

    fun stringToInt(textCurrency: String): Int {
        val value = textCurrency.replace("[^\\d-]".toRegex(), "")
        return value.toIntOrNull() ?: 0
    }

    // --- Helper for number formatting (KMP safe) ---
    fun formatWithSeparator(number: Double, decimals: Int): String {
        val scale = 10.0.pow(decimals)
        val rounded = round(number * scale) / scale

        // Ganti baris ini
        // val str = "%.${decimals}f".format(rounded)
        // dengan yang ini:
        val str = rounded.format(decimals)

        val parts = str.split(".")
        val intPart = parts[0].reversed().chunked(3).joinToString(".").reversed()

        val decimalPart = if (decimals > 0 && parts.size > 1) {
            "." + parts[1]
        } else if (decimals > 0) {
            "." + "".padEnd(decimals, '0')
        } else {
            ""
        }

        return intPart + decimalPart
    }

    private fun Double.format(decimals: Int): String {
        val factor = 10.0.pow(decimals)
        val rounded = round(this * factor) / factor

        val wholePart = rounded.toLong()
        val fractionalPart = ((rounded - wholePart) * factor).toLong()

        val fractionalString = if (decimals > 0) {
            ".${fractionalPart.toString().padStart(decimals, '0')}"
        } else {
            ""
        }

        return "$wholePart$fractionalString"
    }
}
