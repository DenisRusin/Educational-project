package homework7;

import homework7.task1.UniqueValues;
import homework7.task2.Anagram;
import homework7.task3.PowerfulSet;

import java.util.*;


public class Test {
    public static void main(String[] args) {
        // Задача 1

       /* UniqueValues uniqueValues = new UniqueValues();

        ArrayList<String> names = new ArrayList<>(); // Проверяем работу со списком строк
        names.add("Максим");
        names.add("Светлана");
        names.add("Иван");
        names.add("Ольга");
        names.add("Максим");
        names.add("Пётр");
        names.add("Олег");
        names.add("Иван");
        names.add("Ольга");
        names.add("Максим");

        ArrayList<Integer> numbers = new ArrayList<>(); // проверяем работу с числами
        numbers.add(15);
        numbers.add(7);
        numbers.add(15);
        numbers.add(7);
        numbers.add(9);
        numbers.add(20);

        System.out.println(uniqueValues.getUniqueValues(names));
        System.out.println(uniqueValues.getUniqueValues(numbers));

        //----------------------------------------------------------------------------

        // Задача 2

        Anagram anagram = new Anagram();

        System.out.println("Введите 2 строки");

        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();
        System.out.println(anagram.checkAnagram(str1, str2));*/

        //-------------------------------------------------------------------------

        // Задача 3

        PowerfulSet powerfulSet = new PowerfulSet();
        Set<Integer> intersections = new HashSet<>();
        intersections.add(1);
        intersections.add(2);
        intersections.add(3);

        Set<Integer> intersections2 = new HashSet<>();
        intersections2.add(0);
        intersections2.add(1);
        intersections2.add(2);
        intersections2.add(4);

        System.out.println(powerfulSet.intersection(intersections, intersections2));
        System.out.println(powerfulSet.union(intersections, intersections2));
        System.out.println(powerfulSet.relativeComplement(intersections, intersections2));
    }
}
