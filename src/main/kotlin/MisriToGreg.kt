fun misriToGreg(day: Int, month: String, year: Int): String {

    // misri date
    val nearestLowerKeyForMCentury = Table1Stage1.keys.filter { it <= year }.max()
    val ordinalCorrespondingMCenturyYear = Table1Stage1[nearestLowerKeyForMCentury]!!

    val yearDifference = year - nearestLowerKeyForMCentury
    val ordinalForYearDifference = Table1Stage2[yearDifference]!!

    val ordinalForMonth = Table1Stage3v2[month]!!

    val totalOrdinal = ordinalCorrespondingMCenturyYear + ordinalForYearDifference + ordinalForMonth + day

    // gregorian date
    val nearestLowerKeyForOrdinalDate = Table2Stage1.values.filter { it <= totalOrdinal }.max()
    val gregYearForNearestOrdinalEntry = Table2Stage1.entries.first { it.value == nearestLowerKeyForOrdinalDate }
    val gregYearForNearestOrdinal = gregYearForNearestOrdinalEntry.key

    val ordinalDifference = totalOrdinal - nearestLowerKeyForOrdinalDate
    val nearestLowerKeyForGYear = Table2Stage2.values.filter { it <= ordinalDifference }.max()
    val gregYearForOrdinalDifferenceEntry = Table2Stage2.entries.first { it.value == nearestLowerKeyForGYear }
    val gregYearForOrdinalDifference = gregYearForOrdinalDifferenceEntry.key

    val remainingDays = ordinalDifference - nearestLowerKeyForGYear
    val nearestLowerKeyForGMonth = Table2Stage3v2.values.filter { it <= remainingDays }.max()
    val gregMonthDayForRemainingDaysEntry = Table2Stage3v2.entries.first { it.value == nearestLowerKeyForGMonth }
    val gregMonthDayForRemainingDays = gregMonthDayForRemainingDaysEntry.key

    val gregYear = gregYearForNearestOrdinal + gregYearForOrdinalDifference
    val gregMonth = gregMonthDayForRemainingDays.first
    val gregDay = remainingDays - nearestLowerKeyForGMonth

    return "$gregDay-$gregMonth-$gregYear"
}
fun main(){

    // "Muharram"
    //"Safar"
    //"Rabiul Awwal"
    //"Rabius Sani"
    //"Jumadal Awwal"
    //"Jumadas Sani"
    //"Rajab"
    //"Shaban"
    //"Ramadan"
    //"Shawwal"
    //"Dhul Qada"
    //"Dhul Hajja"

    val mDate = "5-Shaban-1445"
    val mDateParts = mDate.split("-")

    val day = mDateParts[0].toInt()
    val month = mDateParts.subList(1, mDateParts.size - 1).joinToString(" ")
    val year = mDateParts[mDateParts.size - 1].toInt()

    println("Misri Date: $day $month $year")

    val gregDate = misriToGreg(day, month, year)
    println(gregDate)

}