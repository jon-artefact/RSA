import java.math.BigInteger

object Main {
    val ANSI_RESET = "\u001B[0m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_PURPLE = "\u001B[35m"

    @JvmStatic fun main(args: Array<String>) {
        val bob = Sender("Bob", BigInteger("3"))
        val eve = Hacker()

        val cypher1 = RSA.encrypt(BigInteger("1337"), bob.publicKey)
        bob.decrypt(cypher1)

        val cypher2 = RSA.encrypt(BigInteger("1338"), bob.publicKey)
        bob.decrypt(cypher2)

        val result = eve.crack(cypher1, cypher2, bob.publicKey.n)
        if(result == BigInteger("1337"))
            println("$ANSI_RED OMG you have been hacked! $ANSI_RESET")
    }
}
