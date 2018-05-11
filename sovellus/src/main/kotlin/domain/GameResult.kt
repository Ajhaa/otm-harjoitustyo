package domain

import domain.Enums.Champion
import domain.Enums.Rank
import domain.Enums.Result

/**
 * Gameresult consists of a rank, a result(win/loss) and a champion
 */
class GameResult(val rank: Rank, val result: Result, val champion: Champion) {

    override fun toString(): String {
        return "$result, $rank ($champion)"
    }
}