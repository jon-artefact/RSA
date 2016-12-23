import main.java.RSA
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RSATest {

    @Test
    fun `should return 1`() {
        assertEquals(BigInteger("1"), RSA.ONE)
    }

    @Test
    fun `should return 2`() {
        assertEquals(BigInteger("2"), RSA.TWO)
    }

    @Test
    fun `should be primal`() {
        assertTrue(RSA.primalTestFermat(BigInteger("3")))
    }

    @Test
    fun `should create keys`() {
        val keys = RSA.keygen()
        assertNotNull(keys)
        assertTrue(RSA.primalTestFermat(keys.first.e))
    }

    @Test(expected = RSA.NotPrimalNumberException::class)
    fun `key`() {
        assertNotNull(RSA.keygen(RSA.TWO))
    }

    @Test
    fun `should be able to encrypt and decrypt`() {
        val keys = RSA.keygen()
        val message = BigInteger("1337")
        val cypher = RSA.encrypt(message, keys.first)
        assertEquals(message, RSA.decrypt(cypher, keys.second))
    }

}