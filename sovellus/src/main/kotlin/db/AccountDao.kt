package db

import domain.Account
import java.sql.*

class AccountDao(val connection: Connection){

    fun getAll() : List<Account> {
        val statement = connection.createStatement()
        val result = statement.executeQuery("SELECT * FROM Account")

        val accounts = ArrayList<Account>()
        while (result.next()) {
            accounts.add(Account(result.getString("name")))
        }
        result.close()
        statement.close()

        return accounts
    }

    fun add(account: Account) {
        val statement = connection.prepareStatement("INSERT INTO Account (name) values(?)")
        statement.setString(1, account.name)

        statement.execute()
        statement.close()
    }

}