package homework3;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Задача 1

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите свое имя");
        String name = scanner.nextLine(); // Сохраняем введеную строку из консоли
        System.out.println("Привет, " + name);

        // Задача 2

        String player1 = "Вася";
        String player2 = "Петя";

        int figurePlayer1 = new Random().nextInt(3); // Случайные результаты каждого игрока
        int figurePlayer2 = new Random().nextInt(3);

        System.out.println(player1 + " показал " + figurePlayer1 + " " +  player2 + " показал " + figurePlayer2);

        if(figurePlayer1 == figurePlayer2){ // Если ничья
            System.out.println("Ничья");
        }else if((figurePlayer1 == 0 && figurePlayer2 == 1) || (figurePlayer1 == 1 && figurePlayer2 == 2) ||
                (figurePlayer1 == 2 && figurePlayer2 == 0)){ // Все выигрышные комбинации Васи
            System.out.println("Победил " + player1);
        }else {
            System.out.println("Победил " + player2); // Значит победил Петя
        }
    }
}