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
 * @constructor empty constructor
 */

class AccountManager(val accountDao: AccountDao) {
    private var currentAccount: Account? = null
    private var accounts = accountDao.getAll()


    fun login(loginName: String): Boolean {
        val account = accounts.find { n -> n.name == loginName }

        if (account != null) {
            currentAccount = account
            return true
        }

        return false
    }

    /**
     * errors is current account is null, call safely
     */
    fun getCurrentAccount(): Account {
        return currentAccount!!
    }

    fun createAccount(accountName: String): Boolean {
        val account = Account(accountName, ArrayList())
        if (!accounts.contains(account)) {
            accounts.add(account)
            accountDao.add(account)
            return true
        }
        return false
    }

    fun addResult(rank: Rank, result: Result): Boolean {
        if (currentAccount == null) {
            return false
        }
        val newResult = GameResult(rank, result)
        val status = currentAccount?.addResult(newResult)
        accountDao.addResult(newResult, currentAccount!!.name)
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