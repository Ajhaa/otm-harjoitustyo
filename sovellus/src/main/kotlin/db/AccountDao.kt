package db

import domain.Account
import domain.GameResult
import java.sql.*

class AccountDao(val connection: Connection){

    val resultDao = ResultDao(connection)

    fun getAll() : ArrayList<Account> {
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

    fun findByName(name: String) : Account? {
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

    fun findAccountId(name: String) : Int{
        val statement = connection.prepareStatement("SELECT * FROM Account WHERE name = ?")
        statement.setString(1, name)
        val result = statement.executeQuery()

        return result.getInt("id")
    }

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