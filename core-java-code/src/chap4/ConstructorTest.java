package chap4;

import java.util.Random;

public class ConstructorTest {

    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Harry", 4000);
        staff[1] = new Employee(6000);
        staff[2] = new Employee();

        for (Employee e : staff) {
            System.out.println(e.toString());
        }
    }

}

class Employee {
    private static int nextId;
    private int id;
    private String name;
    private double salary;

    static {
        Random random = new Random();
        nextId = random.nextInt(1000);
    }

    {
        id = nextId;
        nextId++;
    }

    public Employee() {
    }

    public Employee(double salary) {
        this("Employee $" + nextId, salary);
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + "  salary: " + salary;
    }
}