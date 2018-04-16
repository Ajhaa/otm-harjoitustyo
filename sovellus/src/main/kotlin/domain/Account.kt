package domain

/**
 * A simple account class
 * @param name The name of the account. Must be unique.
 * @constructor currently only defines name
 */
class Account(var name: String) {

    var results = ArrayList<GameResult>()

    fun addResult(res: GameResult) {
        results.add(res)
    }
    /**
     * compares two account objects by name
     * @param other the target of comparision
     */
    override fun equals(other: Any?): Boolean {
        if (other !is Account) {
            return false
        }

        return other.name == this.name

    }

    override fun toString(): String {
        return name
    }
}