package domain

import db.AccountDao
import domain.Enums.Rank
import domain.Enums.Result

/**
 * The AccountManager class keeps track of the registered accounts, and
 * handles login and logout(TODO)
 *
 * @property currentAccount currently logged account. Null if nobody logged in
 * @property accounts set of all registered accounts.
 * @constructor accountDao given with constructor
 */

class AccountManager(val accountDao: AccountDao) {
    private var currentAccount: Account? = null
    private var accounts = accountDao.getAll()

    /**
     * logs in a new account.
     * @param loginName name entered to login
     * @return true if successful, false otherwise
     */
    fun login(loginName: String): Boolean {
        val account = accounts.find { n -> n.name == loginName }

        if (account != null) {
            currentAccount = account
            return true
        }

        return false
    }

    /**
     * returns current account without checking if its null.
     * errors is current account is null, call safely
     * @return the corrent logged in account
     */
    fun getCurrentAccount(): Account {
        return currentAccount!!
    }

    /**
     * creates a new account, adds it to the list of accounts and the database.
     * @param accountName name of the new account
     */

    fun createAccount(accountName: String): Boolean {
        val account = Account(accountName, ArrayList())
        if (!accounts.contains(account)) {
            accounts.add(account)
            accountDao.add(account)
            return true
        }
        return false
    }

    /**
     * adds a new result for the current account.
     * @param rank new ranking of player
     * @param result either win or loss
     * @return true if the action was successful, false otherwise
     */

    fun addResult(rank: Rank, result: Result): Boolean {
        if (currentAccount == null) {
            return false
        }
        val newResult = GameResult(rank, result)
        val status = currentAccount?.addResult(newResult)
        accountDao.addResult(newResult, currentAccount!!.name)
        return status != null
    }

    /**
     * returns the highest rank the current account has ever been. Useful for statistics.
     * @return current accounts highest rank in history
     */
    fun getHighestRankValue(): Int {
        return currentAccount!!.results
                .stream()
                .mapToInt { n -> n.rank.getRankAsNumber() }
                .max()
                .asInt
    }
}