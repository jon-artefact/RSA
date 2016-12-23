/**
 * Created by John.
 */
import java.math.BigInteger

class Hacker @JvmOverloads internal constructor(private val name: String = "Eve") {

    fun crack(c1: BigInteger, c2: BigInteger, n: BigInteger): BigInteger {
        return ((c2 - c1 + RSA.TWO).modInverse(n) * (c2 + RSA.TWO * c1 - RSA.ONE)) % n
    }
}