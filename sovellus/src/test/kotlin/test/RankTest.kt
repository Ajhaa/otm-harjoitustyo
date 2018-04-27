package test

import domain.Enums.Rank
import domain.Enums.Tier
import org.junit.Test
import kotlin.test.assertTrue

class RankTest {

    @Test
    fun decreaseRankSimple1() {
        val rank = Rank(Tier.Bronze , 3)
        val newRank = rank.decreaseRank()
        assertTrue { newRank == Rank(Tier.Bronze, 2) }
    }

    @Test
    fun increaseRankSimple1() {
        val rank = Rank(Tier.Bronze, 3)
        val newRank = rank.increaseRank()
        assertTrue { newRank == Rank(Tier.Bronze, 4) }
    }

    @Test
    fun increaseToNextTier() {
        val rank = Rank(Tier.Bronze, 5)
        val newRank = rank.increaseRank()
        assertTrue { newRank == Rank(Tier.Silver, 1) }
    }

    @Test
    fun decreaseToPreviousTier() {
        val rank = Rank(Tier.Silver, 1)
        val newRank = rank.decreaseRank()
        assertTrue { newRank == Rank(Tier.Bronze, 5) }
    }
}