package org.example.task1;

import java.util.*;
import java.util.stream.Collectors;

public class Task1 {

    public static long getQty(int[] arr) {

        return Arrays.stream(arr)
                .filter(num -> num*num < 100_000)
                .count();
    }

    public static long getQty(ArrayList<Integer> lst) {

        return lst.stream()
                .filter(num -> num*num < 100_000)
                .count();
    }

    public static void getArithmSum(ArrayList<Integer> lst) {

        List<Integer> sumList;

        sumList = lst.stream()
                .filter(num -> num > 500_000)
                .map(num -> num * 5 - 150)
                .collect(Collectors.toList());

        Long sum = (long) sumList.stream()
                .mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    public static void getArithmSum(int [] arr) {

        long sum = Arrays.stream(arr)
                .filter(num -> num > 500_000)
                .map(num -> num * 5 - 150)
                .sum();
        System.out.println(sum);
    }

    public static ArrayList<Integer> getIntegerList() {
        return new Random()
                .ints(1000, 1, 1_000_000)
                .boxed()
                .collect(ArrayList::new, List::add, List::addAll);
    }

    public static int[] getIntsArray() {
        return new Random().ints(1000, 1, 1_000_000).toArray();
    }

    public static void getMax(int [] numbers) {

        try {
            int max = Arrays.stream(numbers)
                    .max().orElseThrow(NullPointerException::new);
            System.out.println(max);

        }catch (NullPointerException e){
            System.err.println("Empty List");
        }
    }

    public static void getMax(ArrayList<Integer> lst) {

        try {
            Integer max = lst.stream()
                    .max(Comparator.naturalOrder())
                    .orElseThrow(NullPointerException::new);
            System.out.println(max);

        }catch (NullPointerException e){
            System.err.println("Empty List");
        }
    }
}
