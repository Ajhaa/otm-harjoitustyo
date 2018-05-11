package db

import domain.Account
import domain.GameResult
import java.sql.Connection

/**
 * used to access the Account table, and indirectly also the result table in the database
 */
open class AccountDao(val connection: Connection) {

    val resultDao = ResultDao(connection)
    /**
     * returns all of the accounts in the database as a list. Uses result dao to fetch the corresponding results
     */
    open fun getAll(): ArrayList<Account> {
        val statement = connection.createStatement()
        val result = statement.executeQuery("SELECT * FROM Account")

        val accounts = ArrayList<Account>()
        while (result.next()) {
            val results = resultDao.getByAccountId(result.getInt("id"))
            accounts.add(Account(result.getString("name"), results))
        }
        result.close()
        statement.close()

        return accounts
    }

    /**
     * used to find a single account by name. returns null if no account found
     * @param name the name of the account
     */
    open fun findByName(name: String): Account? {
        val statement = connection.prepareStatement("SELECT * FROM Account WHERE name = ?")
        statement.setString(1, name)
        val result = statement.executeQuery()

        if (!result.next()) {
            return null
        }

        val id = result.getInt("id")
        val results = resultDao.getByAccountId(id)

        return Account(result.getString("name"), results)
    }

    /**
     * finds the id of an account
     */
    private fun findAccountId(name: String): Int {
        val statement = connection.prepareStatement("SELECT * FROM Account WHERE name = ?")
        statement.setString(1, name)
        val result = statement.executeQuery()

        return result.getInt("id")
    }

    /**
     * adds a new result for the acccount whose name is given in the parameter
     */
    open fun addResult(result: GameResult, name: String) {
        resultDao.add(result, findAccountId(name))
    }

    /**
     * adds a new account to the database
     */
    open fun add(account: Account) {
        val statement = connection.prepareStatement("INSERT INTO Account (name) values(?)")
        statement.setString(1, account.name)

        statement.execute()
        statement.close()
    }

    /**
     * removes and account and all related results
     */
    open fun deleteAccount(name: String) {
        val id = findAccountId(name)
        val statement = connection.prepareStatement("DELETE * FROM Result WHERE account_id=?")
        statement.setInt(1, id)
        statement.execute()
        val statement2 = connection.prepareStatement("DELETE * FROM Account WHERE name=?")
        statement2.setString(1, name)
        statement.execute()
        statement.close()
        statement2.close()
    }
}