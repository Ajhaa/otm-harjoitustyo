package domain.Enums

class Rank(tier: Tier, phase: Int) {
    val tier = tier
    val phase = phase

    fun getRankAsNumber(): Int {
        return tier.ordinal * 5 + phase
    }

    override fun toString(): String {
        return "$tier $phase"
    }
}