package domain

import domain.Enums.Rank
import domain.Enums.Result

class GameResult(val rank: Rank, val result: Result) {

    override fun toString(): String {
        return "$result, $rank"
    }

}