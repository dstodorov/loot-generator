package com.dst.lootgenerator.auth.controllers;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = Arrays.stream(input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxFromRight = numbers[numbers.length - 1];

        for (int i = numbers.length - 2; i >= 0; i--) {
            if (numbers[i] > maxFromRight) {
                maxFromRight = numbers[i];
            }
        }
        
        System.out.println(maxFromRight);
    }
}