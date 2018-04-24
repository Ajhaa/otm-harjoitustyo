package test
import db.UserDao
import org.junit.After
import org.junit.Before
import java.sql.DriverManager
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DatabaseTest {
    val connection = DriverManager.getConnection("jdbc:sqlite:./db/database.db")
    val userDao = UserDao(connection)

    @Before
    fun setup() {
        emptyDb()
    }
    @Test
    fun getAllReturnsData() {
        val statement = connection.prepareStatement("INSERT INTO User (name) values(\"testiuseri\")")
        statement.execute()

        val result = userDao.getAll();
        assertTrue { !result.isEmpty() }
    }


    @After
    fun close() {
        emptyDb()
        connection.close()
    }

    fun emptyDb() {
        val statement = connection.prepareStatement("DELETE FROM User")
        statement.execute()
        statement.close()
    }


}