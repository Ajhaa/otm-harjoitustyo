package test

import db.AccountDao
import db.Database
import domain.AccountManager
import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier
import org.junit.Before
import org.junit.Test
import java.sql.DriverManager
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AccountManagerTest {
    val database = Database("jdbc:sqlite:test.db")
    var manager = AccountManager(AccountDao(database.connection))

    @Before
    fun setup() {
        manager = AccountManager(AccountDao(database.connection))
    }

    @Test
    fun loginFailsWithNoAccountCreated() {
        assertFalse(manager.login("nowork"))
    }

    @Test
    fun loginSuccessfulWhenAccountCreated() {
        manager.createAccount("test")
        assertTrue(manager.login("test"))
    }

    @Test
    fun loginFailsWithWrongAccountName() {
        manager.createAccount("atte")
        assertFalse(manager.login("otso"))
    }

    @Test
    fun addingResultToNullReturnsFalse() {
        val b = manager.addResult(Rank(Tier.Bronze, 1), Result.Win)
        assertFalse(b)
    }
}