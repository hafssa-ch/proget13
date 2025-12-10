package Exercice1;

import java.io.Serializable;


public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int    id;          
    private final String name;        
    private final double salary;      
    private transient String password; 

    public Employee(int id, String name, double salary, String password) {
        this.id       = id;
        this.name     = name;
        this.salary   = salary;
        this.password = password;
    }

    public int    getId()       { return id; }
    public String getName()     { return name; }
    public double getSalary()   { return salary; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return String.format(
            "Employee[id=%d, name=%s, salary=%.2f, password=%s]",
            id, name, salary,
            (password == null ? "null" : password)
        );
    }
}
