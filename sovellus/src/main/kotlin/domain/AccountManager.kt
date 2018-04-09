package domain

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


    fun login(loginName: String) : Boolean {
        for (acc in accounts) {
            if (acc.name == loginName) {
                currentAccount = acc
                return true
            }
        }
        return false
    }

    fun createAccount(accountName: String) : Boolean {
        return accounts.add(Account(accountName))
    }
}