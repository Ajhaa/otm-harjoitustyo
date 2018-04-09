package domain

class AccountManager {
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