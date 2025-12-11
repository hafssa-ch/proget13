 # TP13 — Sérialisation en Java

Cours : Fondamentaux et Concepts Avancés de la Programmation Java

Ce TP est composé de deux exercices complets :
 Sérialisation & Désérialisation (Serializable)
 Sérialisation Avancée (Externalizable & contrôle du flux)

### Objectifs pédagogiques

✔ Comprendre le mécanisme de sérialisation en Java
✔ Manipuler ObjectOutputStream et ObjectInputStream
✔ Utiliser Serializable, serialVersionUID et transient
✔ Contrôler manuellement la sérialisation via Externalizable
✔ Mettre en œuvre writeExternal() / readExternal()
✔ Gérer les exceptions I/O et la compatibilité de version

### Structure globale du TP
TP13/
├─ Exercice 1/        
│   └─ src/
│       ├ Employee.java
│       ├ SerializationUtil.java
│       └ Main.java
│
└─ Exercice 2/     
    └─ src/
        ├ ChatMessage.java
        ├ ChatHistory.java
        └ Main.java

## EXERCICE 1 — Sérialisation et Désérialisation (Serializable)
### Objectifs

Sérialiser une liste d’objets métier

Désérialiser depuis un fichier binaire

Utiliser serialVersionUID pour éviter InvalidClassException

Exclure des champs sensibles via transient

Utiliser try-with-resources pour la fermeture automatique des flux

### Classe Métier — Employee.java

#### Fonctionnalités :

Classe sérialisable (Serializable)

Champ sensible password ignoré grâce à transient

Version de classe fixée via serialVersionUID

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int    id;
    private final String name;
    private final double salary;
    private transient String password;
    ...
}


#### Pourquoi transient ?
Pour éviter de sérialiser des informations sensibles (mot de passe).

### Utilitaire — SerializationUtil.java

Deux méthodes statiques :

serializeEmployees() → écrire une liste dans un fichier

deserializeEmployees() → relire la liste

Techniques utilisées :

ObjectOutputStream

ObjectInputStream

try-with-resources

### Programme principal — Main.java

Déroulement :

Création d’une liste d’employés

Sérialisation → employees.ser

Désérialisation

Vérification du champ transient

Sortie typique :

→ Sérialisation réussie dans employees.ser
→ Désérialisation réussie
Employee[id=1, name=Youssef, salary=3000.00, password=null]

 Commandes de compilation/exécution
cd TPSerialization/src
javac com/example/tp/*.java
java com.example.tp.Main

<img width="1362" height="343" alt="image" src="https://github.com/user-attachments/assets/502f64db-8376-4a31-847c-285bde5e48e8" />

## EXERCICE 2 — Sérialisation Avancée avec Externalizable
### Objectifs

Contrôler exactement les données écrites dans le fichier

Implémenter Externalizable

Versionner manuellement le format de sérialisation

Gérer les champs dérivés (length) sans les sérialiser

### Classe Métier — ChatMessage.java

#### Caractéristiques :

Implémente Externalizable

Contrôle total de l’écriture/lecture

Champ dérivé length → recalculé

public class ChatMessage implements Externalizable {
    private static final int FORMAT_VERSION = 1;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(FORMAT_VERSION);
        out.writeUTF(user);
        out.writeUTF(message);
        out.writeLong(timestamp.toEpochMilli());
    }
}


#### Avantage d’Externalizable :
 Vous contrôlez totalement le format binaire (utile pour compatibilité).

 Utilitaire — ChatHistory.java

Fonctionnalités :

Sauvegarder une liste de messages

Lire un nombre variable de messages

Gérer le flux manuellement (taille + messages)

### Programme principal — Main.java

Étapes :

Création d’un historique de chat

Sérialisation manuelle via ChatHistory.save()

Chargement complet

Affichage détaillé

Sortie attendue :

Historique sauvegardé dans chat.ser
Historique chargé :
  [18 chars] 2025-06-14T12:34:56Z [Youssef]: Salam tout le monde

 Commandes de compilation/exécution
cd TPExternalizable/src
javac com/example/tp/*.java
java com.example.tp.Main

 ### Points Clés & Bonnes Pratiques
 Sécurité

Ne jamais sérialiser des mots de passe → transient

 Versionnage

Modifier serialVersionUID après un changement majeur

Avec Externalizable : gérer soi-même FORMAT_VERSION

 Robustesse

Toujours gérer IOException & ClassNotFoundException

 Performance

Utiliser GZIPOutputStream pour réduire la taille des fichiers

Utiliser NIO (Files.newOutputStream()) pour les gros volumes de données

### Extensions possibles

Ajouter chiffrement/déchiffrement dans writeObject() / readObject()

Support JSON/XML en parallèle du binaire

Historique de chat incrémental (append-only)

Outil de migration de version (FORMAT_VERSION++)

<img width="908" height="197" alt="image" src="https://github.com/user-attachments/assets/862d4b3f-da17-4cdc-890d-9d14d6686d46" />

## Conclusion

Ce TP vous permet de maîtriser :
 la sérialisation standard (Serializable)
 la sérialisation avancée (Externalizable)
 la gestion du versionnage
 les bonnes pratiques de sécurité et performance
