package Exercice1;

import java.io.*;

/**
 * SecureEmployee démontre writeObject/readObject pour contrôler la sérialisation
 * (on chiffre/déchiffre le champ password).
 */
public class SecureEmployee implements Serializable {
    private static final long serialVersionUID = 2L;

    private final int id;
    private final String name;
    private final double salary;
    private transient String password; // on évitera la sérialisation "brute"

    public SecureEmployee(int id, String name, double salary, String password) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.password = password;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // écriture par défaut pour id/name/salary
        oos.defaultWriteObject();
        // chiffrement simple du password et écriture
        if (password == null) {
            oos.writeObject(null);
        } else {
            byte[] pb = password.getBytes("UTF-8");
            byte[] cipher = xor(pb, (byte) 0x5A); // XOR byte simple
            oos.writeObject(cipher);
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // lecture par défaut pour id/name/salary
        ois.defaultReadObject();
        Object obj = ois.readObject();
        if (obj == null) {
            this.password = null;
        } else {
            byte[] cipher = (byte[]) obj;
            byte[] pb = xor(cipher, (byte) 0x5A);
            this.password = new String(pb, "UTF-8");
        }
    }

    private static byte[] xor(byte[] data, byte key) {
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++) out[i] = (byte) (data[i] ^ key);
        return out;
    }

    @Override
    public String toString() {
        return String.format("SecureEmployee[id=%d, name=%s, salary=%.2f, password=%s]",
                id, name, salary, (password == null ? "null" : password));
    }
}

