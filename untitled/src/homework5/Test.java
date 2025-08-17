package homework5;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        // 1 задача

        KeyboardLetter keyboardLetter = new KeyboardLetter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите букву: ");
        String letter = scanner.next();
        scanner.nextLine();
        keyboardLetter.getPreviousLetter(letter);


        // 2 задача

        Arrow arrow = new Arrow();
        System.out.println("Введите строку: ");
        String text = scanner.next(); // ">>-->>>>>>>>>>>>>>>-->>>>---<--<<--->>>-->>-->--><>>-<--<<--->>-->---<--<<-->>---<--<<>>>>>>-->";
        scanner.nextLine();
        System.out.println("Колличество стрел; " + arrow.getCountArrow(text));


        // Задача 3

        WordSorting wordSorting = new WordSorting();
        System.out.println("Введите строку: ");
        String myString = scanner.nextLine(); //"DCBA DCBA";
        System.out.println(wordSorting.sortingWord(myString)); // Вывод: abcd abcd

    }
}
