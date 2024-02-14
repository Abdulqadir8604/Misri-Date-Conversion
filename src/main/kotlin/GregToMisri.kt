import java.time.LocalTime


fun main(){

    // gregorian date
    val gregDate = getCurrentDate()

    val gCentury = gregDate.substring(6, 8).toInt() * 100
    val ordinalCorrespondingCentury = Table2Stage1[gCentury]!!

    val gCenturyYear = gregDate.substring(8, 10).toInt()
    val nearestLowerKeyForGCentury = Table2Stage2.keys.filter { it <= gCenturyYear }.max()
    val ordinalCorrespondingGCenturyYear = Table2Stage2[nearestLowerKeyForGCentury]!!

    val gMonth = gregDate.substring(3, 5)
    val yearDifference = gCenturyYear - nearestLowerKeyForGCentury
    val ordinalCorrespondingDifferenceInYear = Table2Stage3v1[Pair(gMonth.toInt(), yearDifference)]!!

    val gDay = gregDate.substring(0, 2).toInt()

    val ordinalDate = ordinalCorrespondingCentury + ordinalCorrespondingGCenturyYear + ordinalCorrespondingDifferenceInYear + gDay

    // misri date
    val nearestLowerKeyForOrdinalDate = Table1Stage1.values.filter { it <= ordinalDate }.max()
    val mCentury = Table1Stage1.entries.first { it.value == nearestLowerKeyForOrdinalDate }.key

    val mYearOrdinalDifference = ordinalDate - nearestLowerKeyForOrdinalDate
    val nearestLowerKeyForMYear = Table1Stage2.values.filter { it <= mYearOrdinalDifference }.max()
    val mCenturyYear = Table1Stage2.entries.first { it.value == nearestLowerKeyForMYear }.key
    val mYear = mCenturyYear + mCentury

    val mMonthOrdinalDifference = mYearOrdinalDifference - nearestLowerKeyForMYear
    val nearestLowerKeyForMMonth = Table1Stage3v1.values.filter { it <= mMonthOrdinalDifference }.max()
    val mMonth = Table1Stage3v1.entries.first { it.value == nearestLowerKeyForMMonth }.key

    val mDay = mMonthOrdinalDifference - nearestLowerKeyForMMonth

    println("Gregorian Date: $gregDate")

    val hour = LocalTime.now().hour
    if (hour in 19..23 || hour in 0..6) {
        println("Hijri Date: ${convertToArabicDigits("${mDay+1} رات $mMonth $mYear")}")
    } else {
        println("Hijri Date: ${convertToArabicDigits("$mDay $mMonth $mYear")}")
    }

}