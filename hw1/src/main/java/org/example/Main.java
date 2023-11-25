package org.example;


import java.util.*;
import static org.example.task1.Task1.*;


public class Main {
    public static void main(String[] args) {

        int[] numbersArr = getIntsArray();
        ArrayList<Integer> numbersList = getIntegerList();
        int [] emptyInt = new int [] {};
        ArrayList<Integer> emptyList = new ArrayList<>();

        getMax(numbersArr);
        getMax(numbersList);
//        getMax(emptyInt);
//        getMax(emptyList);

        getArithmSum(numbersArr);
//        getArithmSum(emptyInt);
//        getArithmSum(new int[] {1,2,3,4,5,6,7,8,9});

        getArithmSum(numbersList);
//        getArithmSum(emptyList);
//        getArithmSum(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));

        System.out.println(getQty(numbersArr));
//        System.out.println(getQty(emptyInt));
//        System.out.println(getQty(new int[] {1,2,3,4,5,6,7,8,9}));

        System.out.println(getQty(numbersList));
//        System.out.println(getQty(emptyList));
//        System.out.println(getQty(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9))));
    }

}