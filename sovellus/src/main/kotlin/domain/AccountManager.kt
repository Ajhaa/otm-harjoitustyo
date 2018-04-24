package domain

import domain.Enums.Rank
import domain.Enums.Result

/**
 * The AccountManager class keeps track of the registered accounts, and
 * handles login and logout(TODO)
 *
 * @property currentAccount currently logged account. Null if nobody logged in
 * @property accounts set of all registered accounts.
 * @constructor empty constructor
 */

class AccountManager() {
    private var currentAccount: Account? = null
    private var accounts = HashSet<Account>()

    fun login(loginName: String): Boolean {
        val account = accounts.find { n -> n.name == loginName }

        if (account != null) {
            currentAccount = account
            return true
        }

        return false
    }

    fun getCurrentAccount(): Account? {
        return currentAccount
    }

    fun createAccount(accountName: String): Boolean {
        return accounts.add(Account(accountName))
    }

    fun addResult(rank: Rank, result: Result): Boolean {
        val newResult = GameResult(rank, result)
        val status = currentAccount?.addResult(newResult)
        return status != null
    }

    fun getHighestRankValue(): Int {
        return currentAccount!!.results
                .stream()
                .mapToInt { n -> n.rank.getRankAsNumber() }
                .max()
                .asInt
    }
}