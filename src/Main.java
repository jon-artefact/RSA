import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Sender bob = null;

        try {
            bob = new Sender("Bob", new BigInteger("3"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        Hacker eve = new Hacker();

        BigInteger plaintext = new BigInteger("1337");
        System.out.println("Plaintext > " + plaintext);

        BigInteger cypher1 = RSA.encrypt(plaintext ,bob.getPublicKey());
        System.out.println("Cypher > " + cypher1);

        System.out.println("Result > " + bob.decrypt(cypher1));

        BigInteger plaintext2 = new BigInteger("1338");
        System.out.println("Plaintext > " + plaintext2);

        BigInteger cypher2 = RSA.encrypt(plaintext2 ,bob.getPublicKey());
        System.out.println("Cypher > " + cypher2);

        System.out.println("Result > " + bob.decrypt(cypher2));

        BigInteger result = eve.crack(cypher1,cypher2, bob.getPublicKey().get(0));
        System.out.println("OMG you have been hacked ! > " + result);
    }
}
