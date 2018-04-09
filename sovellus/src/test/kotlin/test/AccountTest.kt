package test

import domain.Account
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AccountTest {
    @Test
    fun equalsWithSameNameReturnsFalse() {
        val a1 = Account("atte")
        val a2 = Account("atte")
        assertTrue(a1.equals(a2))
    }

    @Test
    fun equalsWithDifferentNameReturnsFalse() {
        val a1 = Account("atte")
        val a2 = Account("otso")
        assertFalse(a1.equals(a2))
    }

    @Test
    fun equalsWorksWithNull() {
        val a1 = Account("atte")
        val a2 = null
        assertFalse(a1.equals(a2))
    }

    @Test
    fun nameCanBeSet() {
        val a1 = Account("atte")
        a1.name = "otso"
        assertEquals("otso", a1.name)
    }
}