package Exercice1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        final String filePath = "employees.ser";

        List<Employee> staff = new ArrayList<>();
        staff.add(new Employee(1, "Youssef", 3000.0, "pwdYoussef"));
        staff.add(new Employee(2, "Fatima",  3500.5, "pwdFatima"));
        staff.add(new Employee(3, "Omar",    2800.75,"pwdOmar"));

        try {
            SerializationUtil.serializeEmployees(filePath, staff);
            System.out.println("→ Sérialisation réussie dans " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur de sérialisation : " + e.getMessage());
            return;
        }

        List<Employee> loaded;
        try {
            loaded = SerializationUtil.deserializeEmployees(filePath);
            System.out.println("→ Désérialisation réussie, objets restaurés :");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur de désérialisation : " + e.getMessage());
            return;
        }

        for (Employee emp : loaded) {
            System.out.println("  " + emp);
        }

        System.out.println("\nNote : le champ 'password' est transient, il n’a pas été enregistré → null.");
    }
}
