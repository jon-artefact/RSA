import java.math.BigInteger;

public class Hacker {
    private String name;

    Hacker() {
        this("Eve");
    }

    Hacker(String name) {
        this.name = name;
    }

    public BigInteger crack(BigInteger c1, BigInteger c2, BigInteger n)
    {
        return c2.subtract(c1).add(new BigInteger("2"))
                .modInverse(n)
                .multiply(c2.add(c1.multiply(new BigInteger("2"))).subtract(new BigInteger("1")))
                .mod(n);
    }


}
