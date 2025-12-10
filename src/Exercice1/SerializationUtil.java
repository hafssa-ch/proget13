package Exercice1;

import java.io.*;
import java.util.List;


public class SerializationUtil {

    /**
     * Sérialise une liste d'employés dans un fichier binaire.
     *
     * @param path chemin du fichier de sortie (e.g. "employees.ser")
     * @param data liste d'Employee à écrire
     * @throws IOException en cas d’erreur d’écriture
     */
    public static void serializeEmployees(String path, List<Employee> data)
            throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(data);
        }
    }

    /**
     * Désérialise une liste d'employés depuis un fichier binaire.
     *
     * @param path chemin du fichier d'entrée
     * @return liste d'Employee restaurée
     * @throws IOException            en cas d’erreur de lecture
     * @throws ClassNotFoundException si la classe Employee a changé
     */
    @SuppressWarnings("unchecked")
    public static List<Employee> deserializeEmployees(String path)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(path))) {
            return (List<Employee>) ois.readObject();
        }
    }
}
