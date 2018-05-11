package test
import db.Database
import db.AccountDao
import db.ResultDao
import domain.Account
import domain.Enums.Champion
import domain.Enums.Rank
import domain.Enums.Result
import domain.Enums.Tier
import domain.GameResult
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DatabaseTest {
    val db = File.createTempFile("test", "db")

    val database = Database("jdbc:sqlite:test.db")
    val connection = database.connection
    val accountDao = AccountDao(connection)
    val resultDao = ResultDao(connection)

    @Before
    fun setup() {
        emptyDb()
    }
    @Test
    fun accountGetAllReturnsData() {
        val statement = connection.prepareStatement("INSERT INTO Account (name) values(\"testiuseri\")")
        statement.execute()

        val result = accountDao.getAll()
        assertTrue { !result.isEmpty() }
    }

    @Test
    fun addAddsAccount() {
        accountDao.add(Account("atte"))
        val statement = connection.prepareStatement("SELECT * FROM Account WHERE name='atte'")
        val result = statement.executeQuery()
        assertTrue { result.next() }
    }

    @Test
    fun resultGetAllReturnsData() {
        val statement = connection.prepareStatement("INSERT INTO Result (account_id, tier, phase, result, champion) values (1, 'Bronze', 1, 'Win', 'Bakko')")
        statement.execute()
        statement.close()
        val result = resultDao.getByAccountId(1)
        assertTrue { result.size == 1 }
    }

    @Test
    fun addAddsResult() {
        val result = GameResult(Rank(Tier.Bronze, 1), Result.Win, Champion.Ashka)
        resultDao.add(result, 1)
        val all = resultDao.getByAccountId(1)
        assertTrue { all.size == 1 }
    }

    @Test
    fun addResultAddsResult() {
        val result = GameResult(Rank(Tier.Bronze, 1), Result.Win, Champion.Ashka)
        accountDao.add(Account("atte"))
        accountDao.addResult(result, "atte")
        val find = accountDao.findByName("atte")
        assertEquals(1, find!!.results.size)
    }

    @After
    fun close() {
        emptyDb()
        connection.close()
    }

    fun emptyDb() {
        val statement = connection.prepareStatement("DELETE FROM Account")
        val statement2 = connection.prepareStatement("DELETE FROM Result")

        statement.execute()
        statement2.execute()
        statement.close()
        statement2.close()
    }
}