package test

import db.AccountDao
import db.Database
import domain.AccountManager
import domain.Enums.Champion
import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier
import domain.StatisticsGenerator
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class StatisticsTest {
    var database = Database("jdbc:sqlite:test.db")
    var manager = AccountManager(AccountDao(database.connection))
    var stats: StatisticsGenerator? = null

    @Before
    fun setup() {
        manager = AccountManager(AccountDao(database.connection))
        manager.createAccount("atte", Tier.Gold, 1)
        manager.login("atte")
    }

    @Test
    fun championStatsReturnsCorrectValues() {
        manager.addResult(Rank(Tier.Gold, 1), Result.Win, Champion.Ashka)
        manager.addResult(Rank(Tier.Gold, 1), Result.Win, Champion.Bakko)
        manager.addResult(Rank(Tier.Gold, 1), Result.Win, Champion.Ashka)
        stats = StatisticsGenerator(manager)
        val results = stats!!.getChampionRates()
        assertEquals(2, results[Champion.Ashka])
    }
}