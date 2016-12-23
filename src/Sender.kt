/**
 * Created by John.
 */

import java.math.BigInteger

class Sender {

    private var keys: Pair<RSA.PublicKey, RSA.PrivateKey>
    val name: String

    @JvmOverloads internal constructor(name: String = "Alice") {
        this.name = name
        this.keys = RSA.keygen()
    }

    @Throws(Exception::class)
    internal constructor(name: String, bigint: BigInteger) {
        this.name = name
        this.keys = RSA.keygen(bigint)
    }

    private val privateKey: RSA.PrivateKey
        get() {
            return this.keys.second
        }

    internal fun decrypt(x: BigInteger): BigInteger {
        return RSA.decrypt(x, privateKey)
    }

    internal val publicKey: RSA.PublicKey
        get() {
            return this.keys.first
        }
}
