import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Сколько обьектов ТВ вы хотите создать?");

        int countTv = scanner.nextInt();
        scanner.nextLine();
        TV[] tv = new TV[countTv]; // создали массив обьектов TV

        for (int i = 0; i < tv.length;  i++) {
            System.out.println("Задайте параметры для TV №" + (i+1));
            System.out.println("Введите название модели:");
            String model = scanner.nextLine();
            System.out.println("Введите название фирмы:");
            String company = scanner.nextLine();;
            System.out.println("Введите размер диагонали экрана:");
            double screenDiagonal = scanner.nextDouble();
            System.out.println("Введите вес:");
            double weight = scanner.nextDouble();;
            System.out.println("Введите цену:");
            double price = scanner.nextDouble();
            scanner.nextLine();

            tv[i] = new TV(model, company, screenDiagonal, weight, price); // создаем обькт TV и задаем параметры через конструктор
        }

        for (TV value : tv) {
            System.out.println("Характеристики " + value); // Просматриваем все характеристики и включаем ТВ
            value.turnOnOffTheTV();
        }

        for (TV value : tv) { // Меняем цену телевизоров
            System.out.println("Меняем цену телевизора " + value.getCompany() + " " + value.getModel());
            value.setPrice(new Random().nextDouble(50000.00));
            System.out.println("Новая цена телевизора " + value.getCompany() + " "  + value.getModel() + " " + value.getPrice());
        }



        for (TV value : tv) {  // Просматриваем все характеристики и выключаем ТВ
            System.out.println("Новые характеристики телевизора " + value);
            value.turnOnOffTheTV();
        }

    }
}
