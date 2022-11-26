package club.aborigen.database.ui.helper;

import java.io.Serializable;

public class Employee implements Serializable {
    private long id;
    private String name;
    private Integer salary;

    public Employee (long id, String name, Integer salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSalary() {
        return salary;
    }
}