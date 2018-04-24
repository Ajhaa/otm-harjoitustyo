package db

import domain.Account
import java.sql.*

class UserDao(val connection: Connection){

    fun getAll() : List<Account> {
        val statement = connection.createStatement()
        val result = statement.executeQuery("SELECT * FROM User")

        val accounts = ArrayList<Account>()
        while (result.next()) {
            accounts.add(Account(result.getString("name")))
        }
        result.close()
        statement.close()

        return accounts
    }

}