import java.math.BigInteger

/**
 * Created by John.
 */
object Main {
    @JvmStatic fun main(args: Array<String>) {
        val bob: Sender?

        try {
            bob = Sender("Bob", BigInteger("3"))
        } catch (e: Exception) {
            System.err.println(e.message)
            return
        }

        val eve = Hacker()

        val plaintext = BigInteger("1337")
        println("Plaintext > " + plaintext)

        val cypher1 = RSA.encrypt(plaintext, bob.publicKey)
        println("Cypher > " + cypher1)

        System.out.println("Result > " + bob.decrypt(cypher1))

        val plaintext2 = BigInteger("1338")
        println("Plaintext > " + plaintext2)

        val cypher2 = RSA.encrypt(plaintext2, bob.publicKey)
        println("Cypher > " + cypher2)

        System.out.println("Result > " + bob.decrypt(cypher2))

        val result = eve.crack(cypher1, cypher2, bob.publicKey.n)
        println("OMG you have been hacked ! > " + result)
    }
}
