package org.example;

import org.example.tasks.Employee;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.tasks.Employee.*;
import static org.example.tasks.Task1.*;


public class Main {
    public static void main(String[] args) {

        int[] numbersArr = getIntsArray();
        ArrayList<Integer> numbersList = getIntegerList();
        int [] emptyInt = new int [] {};
        ArrayList<Integer> emptyList = new ArrayList<>();

        getMax(numbersArr);
        getMax(numbersList);
        getMax(emptyInt);
        getMax(emptyList);

        getArithmSum(numbersArr);
        getArithmSum(emptyInt);

        getArithmSum(numbersList);
        getArithmSum(emptyList);

        System.out.println(getQty(numbersArr));
        System.out.println(getQty(emptyInt));

        System.out.println(getQty(numbersList));
        System.out.println(getQty(emptyList));

        ArrayList<Employee> employees = generateEmployeeList(20);
        employees.forEach(System.out::println);

        groupByDept(employees);
        namesByDept(employees);
        salaryIncrease(employees);
        averSalaryByDept(employees);
    }

    /*
        Method groups employees by departments
    */
    private static void groupByDept(ArrayList<Employee> employees) {
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        employeesByDept.entrySet().forEach(System.out::println);
    }

    /*
      Method groups employees (with names only) by departments with result as LinkedHashMap
    */

    private static void namesByDept(ArrayList<Employee> employees) {
        Map<String, String> namesByDept = employees.stream()
                .collect(Collectors.toMap(Employee::getDepartment,
                        Employee::getName, (emp1, emp2) -> emp1 + ", " + emp2,
                        LinkedHashMap::new));

        namesByDept.entrySet().forEach(System.out::println);
    }

    /*
        Method increases salary of Employee by 20% if current salary is smaller than 10 000
     */
    private static void salaryIncrease(ArrayList<Employee> employees) {
        employees.stream()
                .filter(emp -> emp.getSalary() < 10_000)
                .forEach(emp -> {
                    double oldSalary = emp.getSalary();
                    emp.setSalary(oldSalary * 1.20);
                });

        employees.forEach(System.out::println);
    }

    /*
        Method groups employees by departments and counts average salary of department
     */
    private static void averSalaryByDept(ArrayList<Employee> employees) {
        Map<String, Double> salaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        salaryByDept.entrySet().forEach(System.out::println);
    }
}