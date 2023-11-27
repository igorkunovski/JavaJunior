package org.example.tasks;

import java.util.ArrayList;
import java.util.Random;

public class Employee {

    private static final String [] names = new String[]
            {"Alex", "Bob", "Dan", "Eugen", "Max", "Sergei", "Anton", "Victor", "Anna", "Lena", "Victoria"};
    private static final String [] surnames = new String[]
            {"Doe", "Smith", "McLoud", "Anderson", "Adams", "Butler", "Collins", "Edwards", "Ford", "Hill", "Lee"};
    private static final String [] departments = new String[] {"Sales", "Purchase", "Support", "Test", "Development"};

    private final String name;

    private final String surname;
    private final int age;
    private double salary;
    private final String department;

    public Employee(String name, String surname, int age, double salary, String department) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name + " " + surname;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return '\'' + name + " " + surname + '\'' +
                " " + age +
                " " + salary +
                " '" + department + '\'';
    }

    public static ArrayList<Employee> generateEmployeeList(int qty) {
        ArrayList<Employee> employees = new ArrayList<>();

        for (int i = 0; i < qty; i++){
            employees.add(new Employee(genName(), genSurname(), genAge(), genSalary(), genDept()));
        }

        return employees;
    }

    private static String genSurname() {
        return surnames[new Random().nextInt(surnames.length)];
    }

    private static double genSalary() {
        return 5000 + new Random().nextInt(25_000);
    }

    private static String genDept() {
        return departments[new Random().nextInt(departments.length)];
    }

    private static int genAge() {
        return 18 + new Random().nextInt(65);
    }

    private static String genName() {
        return names[new Random().nextInt(names.length)];
    }
}
