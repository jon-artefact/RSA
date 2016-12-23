import java.math.BigInteger

class Sender : Interlocutor {

    private var keys: Pair<RSA.PublicKey, RSA.PrivateKey>

    @JvmOverloads internal constructor(name: String = "Alice") : super(name) {
        this.keys = RSA.keygen()
    }

    @Throws(Exception::class)
    internal constructor(name: String, bigint: BigInteger) : super(name) {
        this.keys = RSA.keygen(bigint)
    }

    private val privateKey: RSA.PrivateKey
        get() {
            return this.keys.second
        }

    internal fun decrypt(x: BigInteger): BigInteger {
        val m = RSA.decrypt(x, privateKey)
        println("$x is being decrypted to $m")
        return m
    }

    internal val publicKey: RSA.PublicKey
        get() {
            return this.keys.first
        }
}
