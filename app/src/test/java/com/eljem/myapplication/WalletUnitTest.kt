package com.eljem.myapplication

import com.eljem.myapplication.model.entity.Wallet
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WalletUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testWalletIsCreated() {
        // Create a new Wallet instance, with 42 EUR in it
        var wallet = Wallet(42.0)
        // Check that the wallet actually contains 42 EUR
        assertEquals(42.0, wallet.value, 0.001)
    }
}