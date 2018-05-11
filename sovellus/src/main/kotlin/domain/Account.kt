package domain

/**
 * A simple account class
 * Name and result list defined in constructor. If no result list specified, new empty list is created.
 */
class Account(var name: String, val results: ArrayList<GameResult>) {

    constructor(name: String) : this(name, ArrayList())

    /**
     * adds a new GameResult into the result list.
     * @param res the GameResult object to be added
     */

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

    /**
     * returns the name of the account
     */
    override fun toString(): String {
        return name
    }
}