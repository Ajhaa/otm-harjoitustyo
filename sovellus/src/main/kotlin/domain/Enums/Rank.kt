package domain.Enums

class Rank(tier: Tier, phase: Int) {
    val tier = tier
    val phase = phase

    fun getRankAsNumber(): Int {
        return tier.ordinal * 5 + phase
    }

    fun increaseRank() : Rank {
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

    fun decreaseRank() : Rank {
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