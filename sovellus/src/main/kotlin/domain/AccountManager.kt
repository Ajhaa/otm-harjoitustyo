package domain

import db.AccountDao
import domain.Enums.Champion
import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier

/**
 * The AccountManager class keeps track of the registered accounts, and
 * handles login and logout(TODO)
 *
 * @property currentAccount currently logged account. Null if nobody logged in
 * @property accounts set of all registered accounts.
 * @constructor accountDao given with constructor
 */

open class AccountManager(val accountDao: AccountDao) {
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
     * sets the current account to null
     */
    fun logout() {
        currentAccount = null
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
     * also adds a "ghost" result to signify the starting rank
     * @param accountName name of the new account
     * @param initalTier initial tier of the account
     * @param initalPhase inital phase of the account
     */

    fun createAccount(accountName: String, initialTier: Tier, initalPhase: Int): Boolean {
        val account = Account(accountName)
        val result = GameResult(Rank(initialTier, initalPhase), Result.Win, Champion.Alysia)

        if (!accounts.contains(account)) {
            accounts.add(account)
            accountDao.add(account)
            account.addResult(result)
            accountDao.addResult(result, accountName)
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

    fun addResult(rank: Rank, result: Result, champion: Champion): Boolean {
        if (currentAccount == null) {
            return false
        }
        val newResult = GameResult(rank, result, champion)
        val status = currentAccount?.addResult(newResult)
        accountDao.addResult(newResult, currentAccount!!.name)
        return status != null
    }

    /**
     * returns the result without the first entry, which is just for inital rank
     */
    fun getResults(): ArrayList<GameResult> {
        val results = currentAccount!!.results
        val copy = ArrayList(results)
        copy.removeAt(0)
        return copy
    }

    /**
     * Returns the lates result of current account
     */
    fun getLatestResult(): GameResult {
        return currentAccount!!.results.last()
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

    fun removeAccount() {
        val accountToRemove = currentAccount!!
        logout()
        accountDao.deleteAccount(accountToRemove.name)
    }
}