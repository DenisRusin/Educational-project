package homework6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    protected static ArrayList<Person> persons = new ArrayList<>();
    protected static ArrayList<Product> products = new ArrayList<>();
    public static final String NOT_ALLOW_MASK = "^[0-9]+$";
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        System.out.println("Введите покупателей:");
        addPerson(scanner.nextLine());

        System.out.println("Введите продукты:"); // Формат - (Товар цена действие скидки скидка) Сахар = 50 = 2025.09.03,12:00 = 15
        addProduct(scanner.nextLine());

        while (true) {
            System.out.println("Введите покупку (Выход - END)");

            String text = scanner.nextLine();

            if (text.equals("END")) {
                getShoppingList(); // Вывод списка покупок
                System.out.println("Выход");
                break; // выход
            }
            buyProduct(text); // покупаем продукт
        }
    }


    protected static void addPerson(String text) { // добавление покупателя
        String[] person = text.split(";"); // создаем массив строк

        for (String p : person) { // проходимся по каждому эллимету коллекции
            String[] per = p.split("="); // помещаем в массив 2 эллемента 1 Имя 2 Деньги

            if (per[0].trim().isBlank()) { // проверка на пустое имя
                System.out.println("Имя не может быть пустым");
                break;
            } else if (per[0].trim().length() < 3) {// проверка на длинну имени
                System.out.println("Имя не может быть короче 3 символов");
                break;
            } else if (Double.parseDouble(per[1]) < 0) { // проверка на отрецательное колличество денег
                System.out.println("Деньги не могут быть отрицательными");
                break;
            }
            persons.add(new Person(per[0].trim(), Double.parseDouble(per[1]))); // создаем обьект attestation.Person и помещаем в List
        }
    }


    protected static void addProduct(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd,HH:mm");  // Добавление продукта
        String[] product = text.split(";"); // делим строку и помещаем в массив

        for (String p : product) { // проходимся по каждому эллементу
            String[] prod = p.split("="); // 2 элимента, 1 Имя 2 Цена

            if (prod[0].trim().isBlank()) { // проверка на пустое имя
                System.out.println("Название продукта не может быть пустым");
                break;
            } else if (prod[0].trim().length() < 3) {// проверка на длинну имени
                System.out.println("Имя не может быть короче 3 символов");
                break;
            } else if (Double.parseDouble(prod[1]) <= 0) { // проверка на отрецательную цену или равную 0
                System.out.println("Стоимость продукта не может быть отрицательной или равна 0");
                break;
            } else if (prod[0].trim().matches(NOT_ALLOW_MASK)) { // проверка на отрецательную цену или равную 0
                System.out.println("Название продукта не может содержать только цифры");
                break;
            }

            if (prod.length == 2) {
                products.add(new Product(prod[0].trim(), Double.parseDouble(prod[1]))); // создаем обьект attestation.Person и помещаем в List
            } else if (prod.length == 4) { // Если 4 элимента то значит передан товар со скидкой
                products.add(new DiscountProduct(prod[0].trim(), Double.parseDouble(prod[1]),
                        LocalDateTime.parse(prod[2].trim(), formatter), Double.parseDouble(prod[3])));
            }

        }
    }


    protected static void buyProduct(String text) { // добавление продукта в корзину
        String[] personProduct = text.split("-");
        Person pers = null;
        Product prod = null;

        for (Person p : persons) { // проверяем есть ли в системе Покупатель
            if (p.getName().equals(personProduct[0].trim())) {
                pers = p;
                break;
            }
        }

        for (Product p : products) { // проверяем есть ли в системе Продукт
            if (p.getName().equals(personProduct[1].trim())) {
                prod = p;
            }
        }

        if (pers == null) {
            System.out.println("Пользователя с именем " + personProduct[0].trim() + " нет в системе");
            return;
        }
        if (prod == null) {
            System.out.println("Продукта " + personProduct[1].trim() + " нет в системе");
            return;
        }  // Если есть пользователь и есть продукт, то происходит добавление
        if (pers.getAmount() >= prod.getPrice()) { // хватает ли денег на продукт?
            pers.setProductPackage(prod); //Добавляем продукт в пакет
            pers.setAmount(pers.getAmount() - prod.getPrice()); // минусуем стоимость продукта
            System.out.println(pers.getName() + " купил(а) " + prod.getName());
        } else {
            System.out.println(pers.getName() + " не может позволить себе " + prod.getName());
        }
    }


    protected static void getShoppingList() { // вывод списка продуктов для каждого покупателя
        for (Person p : persons) {
            if (p.getProductPackage().isEmpty()) {
                System.out.println(p.getName() + " - Ничего не куплено");
            } else {
                String list = String.valueOf(p.getProductPackage());
                System.out.println(p.getName() + " - " + list.substring(1, list.length() - 1));
            }
        }
    }
}


