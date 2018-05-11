package db

import java.sql.DriverManager

class Database(address: String) {
    val connection = DriverManager.getConnection(address)

    init {
        val createAccount = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Account (" +
                " id integer PRIMARY KEY," +
                " name VARCHAR)")

        val createResult = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Result (" +
                " id integer PRIMARY KEY," +
                " account_id integer," +
                " tier VARCHAR," +
                " phase integer," +
                " result VARCHAR," +
                " champion VARCHAR," +
                " FOREIGN KEY (account_id) REFERENCES Account(id))")

        createAccount.execute()
        createResult.execute()

        createAccount.close()
        createResult.close()
    }

    fun closeConnection() {
        connection.close()
    }
}