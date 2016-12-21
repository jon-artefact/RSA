/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Sender {

    private HashMap<String, BigInteger> keys;
    private String name;

    Sender() {
        this("Alice");
    }

    Sender(String name) {
        this.name = name;
        this.keys = new HashMap<>();
        this.keys = RSA.keygen();
    }

    Sender(String name, BigInteger bigint) throws Exception {
        this.name = name;
        this.keys = new HashMap<>();
        this.keys = RSA.keygen(bigint);
    }

    private ArrayList<BigInteger> getPrivateKey() {
        ArrayList<BigInteger> retour = new ArrayList<>();
        retour.add(this.keys.get("n"));
        retour.add(this.keys.get("d"));
        return retour;
    }

    BigInteger decrypt(BigInteger x) {
        ArrayList<BigInteger> a = getPrivateKey();
        return x.modPow(a.get(1), a.get(0));
    }

    ArrayList<BigInteger> getPublicKey() {
        ArrayList<BigInteger> retour = new ArrayList<>();
        retour.add(this.keys.get("n"));
        retour.add(this.keys.get("e"));
        return retour;
    }
}
