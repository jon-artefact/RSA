import main.java.Hacker
import main.java.RSA
import main.java.Sender
import org.junit.Test

import org.junit.Assert.*
import java.math.BigInteger

/**
 * Created by John.
 */
class HackerTest {
    val v1ct1m : Sender = Sender(name = "Roger", bigint = BigInteger("3"))
    val h4ck3r : Hacker = Hacker()

    @Test
    fun crack() {
        val message = BigInteger("80085")
        val cypher1 = RSA.encrypt(message, v1ct1m.publicKey)
        val cypher2 = RSA.encrypt(message + RSA.ONE, v1ct1m.publicKey)
        assertEquals(message, h4ck3r.crack(cypher1, cypher2, v1ct1m.publicKey.n))
    }

}