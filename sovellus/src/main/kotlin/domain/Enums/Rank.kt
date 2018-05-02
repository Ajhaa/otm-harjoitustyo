package domain.Enums

/**
 * A rank consists of tier, which is an enum, and phase, which is a number. There are
 * 7 tiers. 6 of them have 5 phases, grandmaster has only one.
 */
class Rank(tier: Tier, phase: Int) {
    val tier = tier
    val phase = phase

    /**
     * returns the rank as a integer for easier handling.
     */
    fun getRankAsNumber(): Int {
        return tier.ordinal * 5 + phase
    }

    /**
     * returns a new rank, which is one phase higher than the object it was called from. If phase overflows (goes above 5), increments tier.
     * No effect if rank is grandmaster
     */
    fun increaseRank(): Rank {
        if (this.tier == Tier.GrandChampion) {
            return this
        }
        val tiers = Tier.values()

        var newPhase = this.phase + 1
        var newTier = this.tier
        if (newPhase > 5) {
            newPhase = 1
            newTier = tiers[newTier.ordinal + 1]
        }

        return Rank(newTier, newPhase)
    }

    /**
     * returns a new rank, which is one phase lower than the object it was called from. If phase goes below one, decrements tier.
     * No effect if rank is Bronze 1
     */
    fun decreaseRank(): Rank {
        if (this.tier == Tier.Bronze && this.phase == 1) {
            return this
        }

        val tiers = Tier.values()

        var newPhase = this.phase - 1
        var newTier = this.tier

        if (newPhase < 1) {
            newPhase = 5
            newTier = tiers[newTier.ordinal - 1]
        }

        return Rank(newTier, newPhase)
    }

    override fun toString(): String {
        return "$tier $phase"
    }

    override fun equals(other: Any?): Boolean {
        val o = other as Rank
        return o.tier == tier && o.phase == phase
    }
}