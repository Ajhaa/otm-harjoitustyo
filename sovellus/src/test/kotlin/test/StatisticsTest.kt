package test

import db.Database
import domain.AccountManager
import domain.Enums.Champion
import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier
import domain.StatisticsGenerator
import org.junit.After
import org.junit.Before
import org.junit.Test
import test.mocs.AccountDaoMock
import kotlin.test.assertEquals
import kotlin.test.expect

class StatisticsTest {
    var database = Database("jdbc:sqlite:test.db")
    var accountDao = AccountDaoMock(database.connection)
    var manager = AccountManager(accountDao)
    var stats: StatisticsGenerator? = null

    @Before
    fun setup() {
        accountDao = AccountDaoMock(database.connection)
        manager = AccountManager(accountDao)
        manager.createAccount("atte", Tier.Gold, 1)
        manager.login("atte")

        manager.addResult(Rank(Tier.Gold, 1), Result.Win, Champion.Ashka)
        manager.addResult(Rank(Tier.Gold, 1), Result.Win, Champion.Ashka)
        manager.addResult(Rank(Tier.Gold, 2), Result.Loss, Champion.Alysia)
        stats = StatisticsGenerator(manager)


    }

    @Test
    fun championStatsReturnsCorrectValues() {
        val results = stats!!.getChampionRates()
        assertEquals(2, results[Champion.Ashka])
    }

    @Test
    fun winRateHistoryReturnsCorrectAmountOfEntries() {
        val history = stats!!.winRateHistory()
        assertEquals(3, history.size)
    }

    @Test
    fun getDataReturnsCorrectAmountOfEntries() {
        val data = stats!!.getData()
        assertEquals(3, data.size)
    }

    @Test
    fun getWinrateReturnsCorrectNumber() {
        val winrate = stats!!.getWinRate()
        assertEquals(2.0/3.0, winrate)
    }

    @After
    fun after() {
        manager.removeAccount()
        database.closeConnection()
    }


}