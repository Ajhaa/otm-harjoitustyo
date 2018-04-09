package test

import domain.AccountManager
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class AccountManagerTest {
    var manager = AccountManager()

    @Before
    fun setup() {
        manager = AccountManager()
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
}