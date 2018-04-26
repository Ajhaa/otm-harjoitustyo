package domain

class StatisticsGenerator(val manager: AccountManager) {
    var results = manager.getCurrentAccount()?.results
    val biggestRank = manager.getHighestRankValue()?.toDouble()

    fun getData() : Map<Number, Number> {
        var data = HashMap<Number, Number>()
        var i = 1
        if (results == null) {
            return data
        }
        for (r in results!!) {
            data[i] = r.rank.getRankAsNumber()
            i++
        }

        return data
    }
}