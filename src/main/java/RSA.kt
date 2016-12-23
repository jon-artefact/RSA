package main.java

import java.math.BigInteger
import java.util.*

object RSA {
    val KEY_SIZE = 512

    val ONE = BigInteger("1")
    val TWO = BigInteger("2")

    val encoder = Base64.getEncoder()

    class PublicKey constructor(val e: BigInteger, val n: BigInteger){
        fun toBase64() : String {
            return encoder.encodeToString(e.toByteArray() + n.toByteArray())
        }
    }
    class PrivateKey constructor(val d: BigInteger, val n: BigInteger)

    class NotPrimalNumberException : Exception("Number given is not a primal number")

    fun encrypt(x: BigInteger, e: BigInteger, n: BigInteger): BigInteger {
        return x.modPow(e, n)
    }

    fun encrypt(x: BigInteger, pub: PublicKey): BigInteger {
        println("$x is being encrypted with ${pub.toBase64()}")
        return encrypt(x, pub.e, pub.n)
    }

    fun decrypt(x: BigInteger, d: BigInteger, n: BigInteger): BigInteger {
        return x.modPow(d, n)
    }

    fun decrypt(x: BigInteger, priv: PrivateKey): BigInteger {
        return decrypt(x, priv.d, priv.n)
    }

    fun primalTestFermat(m: BigInteger): Boolean {
        return TWO.modPow(m - ONE, m) == ONE
    }

    fun keygen(): Pair<PublicKey, PrivateKey> {
        var e = BigInteger(16, 2, Random())

        while (!primalTestFermat(e)) {
            e = e.nextProbablePrime()
        }

        return keygen(e)
    }

    @Throws(NotPrimalNumberException::class)
    fun keygen(e: BigInteger): Pair<PublicKey, PrivateKey> {
        if (!primalTestFermat(e))
            throw NotPrimalNumberException()

        var phi_n: BigInteger
        var n: BigInteger
        var p: BigInteger
        var q: BigInteger

        do {
            p = BigInteger(KEY_SIZE, 2, Random())
            while (!primalTestFermat(p)) p = p.nextProbablePrime()
            q = BigInteger(KEY_SIZE, 2, Random())
            while (!primalTestFermat(q)) q = q.nextProbablePrime()
            n = p * q
            phi_n = (p - ONE) * (q - ONE)

        } while (phi_n.gcd(e) != ONE)

        val d = e.modInverse(phi_n)

        val pub = PublicKey(e,n)
        println("A pair of keys was generated\nPublic key: ${pub.toBase64()}")

        return Pair(pub, PrivateKey(d,n))
    }

}
