package Exercice1;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Exemple d'implémentation Externalizable.
 * L'auteur a le contrôle total sur l'ordre et les données écrites.
 */
public class EmployeeExternalizable implements Externalizable {
    private int id;
    private String name;
    private double salary;
    private String password; // réécrit explicitement (ici, sans sécurité)

    // Constructeur public sans-arg requis par Externalizable
    public EmployeeExternalizable() { }

    public EmployeeExternalizable(int id, String name, double salary, String password) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.password = password;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);
        out.writeDouble(salary);
        // choix : n'écrire que si non-null (contrôle de flux)
        out.writeBoolean(password != null);
        if (password != null) out.writeUTF(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = in.readInt();
        this.name = in.readUTF();
        this.salary = in.readDouble();
        boolean hasPwd = in.readBoolean();
        this.password = hasPwd ? in.readUTF() : null;
    }

    @Override
    public String toString() {
        return String.format("EmployeeExternalizable[id=%d, name=%s, salary=%.2f, password=%s]",
                id, name, salary, (password == null ? "null" : password));
    }
}
