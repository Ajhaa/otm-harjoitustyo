package test.mocs

import db.AccountDao
import domain.Account
import domain.GameResult
import java.sql.Connection

class AccountDaoMock(connection: Connection) : AccountDao(connection) {
    var accounts = ArrayList<Account>()

    override fun getAll(): ArrayList<Account> {
        return ArrayList()
    }

    override fun findByName(name: String): Account? {
        return null
    }

    override fun addResult(result: GameResult, name: String) {
    }

    override fun add(account: Account) {
    }

    fun removeAll() {
    }

    override fun deleteAccount(name: String) {
    }
}
