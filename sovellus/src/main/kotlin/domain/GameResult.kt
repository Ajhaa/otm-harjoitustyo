package domain

import domain.Enums.*

class GameResult(val rank: Rank, val result: Result) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}