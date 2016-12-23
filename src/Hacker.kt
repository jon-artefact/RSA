import java.math.BigInteger

class Hacker(legendaryTag : String = "3v3") : Interlocutor(legendaryTag) {

    fun crack(c1: BigInteger, c2: BigInteger, n: BigInteger): BigInteger {
        val cracked = ((c2 - c1 + RSA.TWO).modInverse(n) * (c2 + RSA.TWO * c1 - RSA.ONE)) % n
        println(Main.ANSI_RED + name + Main.ANSI_RESET +
                " is trying to crack the encryption, the result is " +
                Main.ANSI_PURPLE + cracked + Main.ANSI_RESET)
        return cracked
    }
}