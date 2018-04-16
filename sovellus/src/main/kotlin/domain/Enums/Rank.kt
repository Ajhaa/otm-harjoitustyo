package domain.Enums

class Rank(tier: Tier, phase: Int) {
    private val tier = tier
    private val phase = phase

    override fun toString(): String {
        return "$tier $phase"
    }
}