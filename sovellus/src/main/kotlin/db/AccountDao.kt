package db

import domain.Account
import domain.GameResult
import java.sql.Connection

/**
 * used to access the Account table, and indirectly also the result table in the database
 */
class AccountDao(val connection: Connection) {

    val resultDao = ResultDao(connection)
    /**
     * returns all of the accounts in the database as a list. Uses result dao to fetch the corresponding results
     */
    fun getAll(): ArrayList<Account> {
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
    fun findByName(name: String): Account? {
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

    private fun findAccountId(name: String): Int {
        val statement = connection.prepareStatement("SELECT * FROM Account WHERE name = ?")
        statement.setString(1, name)
        val result = statement.executeQuery()

        return result.getInt("id")
    }

    /**
     *
     */
    fun addResult(result: GameResult, name: String) {
        resultDao.add(result, findAccountId(name))
    }

    fun add(account: Account) {
        val statement = connection.prepareStatement("INSERT INTO Account (name) values(?)")
        statement.setString(1, account.name)

        statement.execute()
        statement.close()
    }
}