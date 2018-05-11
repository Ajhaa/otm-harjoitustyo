package test

import db.AccountDao
import db.Database
import domain.AccountManager
import domain.Enums.Champion
import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier
import org.junit.After
import org.junit.Before
import org.junit.Test
import test.mocks.AccountManagerMock
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AccountManagerTest {
    var database = Database("jdbc:sqlite:test.db")
    var manager = AccountManager(AccountDao(database.connection))

    @Before
    fun setup() {
        database = Database("jdbc:sqlite:test.db")
        manager = AccountManager(AccountDao(database.connection))
    }

    @Test
    fun loginFailsWithNoAccountCreated() {
        assertFalse(manager.login("nowork"))
    }

    @Test
    fun loginSuccessfulWhenAccountCreated() {
        manager.createAccount("test", Tier.Bronze, 1)
        assertTrue(manager.login("test"))
    }

    @Test
    fun loginFailsWithWrongAccountName() {
        manager.createAccount("atte", Tier.Bronze, 1)
        assertFalse(manager.login("otso"))
    }

    @Test
    fun addingResultToNullReturnsFalse() {
        val b = manager.addResult(Rank(Tier.Bronze, 1), Result.Win, Champion.Ashka)
        assertFalse(b)
    }

    @Test
    fun successfullyAddingResultReturnsTrue() {
        manager.createAccount("atte", Tier.Bronze, 1)
        manager.login("atte")
        assertTrue { manager.addResult(Rank(Tier.Silver, 2), Result.Win, Champion.Ashka) }
    }

    @Test
    fun getHighestRankValueReturnsTheHighest() {
        manager.createAccount("atte", Tier.Bronze, 1)
        manager.login("atte")
        manager.addResult(Rank(Tier.Gold, 2), Result.Loss, Champion.Ashka)
        manager.addResult(Rank(Tier.Silver, 2), Result.Win, Champion.Ashka)
        assertEquals(manager.getHighestRankValue(), Rank(Tier.Gold, 2).getRankAsNumber())
    }

    @After
    fun after() {
        database.closeConnection()
    }
}