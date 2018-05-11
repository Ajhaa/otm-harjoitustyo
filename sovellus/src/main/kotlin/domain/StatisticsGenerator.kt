package domain

import domain.Enums.Champion
import domain.Enums.Result
import kotlin.collections.HashMap

/**
 * processes players match data for statistics display
 */

class StatisticsGenerator(val manager: AccountManager) {
    var results = manager.getResults()
    val biggestRank = manager.getHighestRankValue().toDouble()

    /**
     * @return a map with number of the result as key and rank as value
     */
    fun getData(): Map<Number, Number> {
        var data = HashMap<Number, Number>()
        var i = 1

        for (r in results) {
            data[i] = r.rank.getRankAsNumber()
            i++
        }

        return data
    }

    /**
     * calculates and returns the win rate of the current player
     */
    fun getWinRate(): Double {
        var wins = 0
        for (r in results) {
            if (r.result == Result.Win) {
                wins++
            }
        }

        return (wins / results.size.toDouble())
    }

    /**
     * returns history of the player's winrate in a <Int, Double> map
     */
    fun winRateHistory(): Map<Int, Double> {
        val stats = HashMap<Int, Double>()

        var matches = 1
        var wins = 0
        for (r in results) {
            if (r.result == Result.Win) {
                wins++
            }
            stats[matches] = wins.toDouble() / matches
            matches++
        }

        return stats
    }

    /**
     * returns champion in a map sorted by games played
     */
    fun getChampionRates(): Map<Champion, Int> {
        val championData = HashMap<Champion, Int>()

        for (r in results) {
            championData.putIfAbsent(r.champion, 0)
            val increase = championData[r.champion]!! + 1
            championData.put(r.champion, increase)
        }

        return championData.toList().sortedBy { (_, value) -> value }.reversed().toMap()
    }
}