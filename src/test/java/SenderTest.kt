import main.java.RSA
import main.java.Sender
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class SenderTest {

    val alice : Sender = Sender(name = "Alice")
    val bob : Sender = Sender(name = "Bob", bigint = BigInteger("3"))

    @Test
    fun `should define name`() {
        assertNotNull(alice)
        assertNotNull(bob)
    }

    @Test
    fun `should return public key`() {
        assertNotNull(alice.publicKey)
        assertNotNull(bob.publicKey)
    }

    @Test
    fun `should be able to decrypt`() {
        val message = BigInteger("42")
        var cypher = RSA.encrypt(message, alice.publicKey)
        assertEquals(message, alice.decrypt(cypher))
        cypher = RSA.encrypt(message, bob.publicKey)
        assertEquals(message, bob.decrypt(cypher))
    }
}