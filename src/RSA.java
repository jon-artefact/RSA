import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jonat on 21/12/2016.
 */
public class RSA {
    public static int KEY_SIZE = 512;

    public static BigInteger encrypt(BigInteger x, BigInteger e, BigInteger n) {
        return x.modPow(e, n);
    }

    public static BigInteger encrypt(BigInteger x, ArrayList<BigInteger> pub) {
        return x.modPow(pub.get(1), pub.get(0));
    }

    public static BigInteger decrypt(BigInteger x, BigInteger d, BigInteger n) {
        return x.modPow(d, n);
    }

    public static BigInteger decrypt(BigInteger x, ArrayList<BigInteger> priv) {
        return x.modPow(priv.get(1), priv.get(0));
    }

    public static boolean primalityTestFermat(BigInteger m) {
        BigInteger nn = new BigInteger("2");
        return nn.modPow(m.subtract(new BigInteger("1")), m).equals(new BigInteger("1"));
    }

    public static HashMap<String, BigInteger> keygen() {
        BigInteger e = new BigInteger(16, 2, new Random());
        while (!primalityTestFermat(e)) {
            e = e.nextProbablePrime();
        }

        HashMap<String, BigInteger> result = new HashMap<>();
        try {
            result = keygen(e);
        } catch (Exception ex)
        {
            System.err.println("Fatal error in keygen, e was not primal");
        }

        return result;
    }

    public static HashMap<String, BigInteger> keygen(BigInteger e) throws Exception{
        if(!primalityTestFermat(e))
            throw new Exception("e must be primal!");

        HashMap<String, BigInteger> keygen = new HashMap<>();

        BigInteger phi_n, n, d, p, q;

        do {
            p = new BigInteger(KEY_SIZE, 2, new Random());
            while (!primalityTestFermat(p)) {
                p = p.nextProbablePrime();
            }
            q = new BigInteger(KEY_SIZE, 2, new Random());
            while (!primalityTestFermat(q)) {
                q = q.nextProbablePrime();
            }
            n = p.multiply(q);
            phi_n = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));

        } while (!phi_n.gcd(e).equals(new BigInteger("1")));

        d = e.modInverse(phi_n);

        keygen.put("n", n);
        keygen.put("e", e);
        keygen.put("d", d);

        return keygen;
    }

}
