import java.util.Arrays;

public class WordSorting {

    public String sortingWord(String text) {

        String[] words = text.split(" "); // делим строку по пробелам и содаем массив

        for (int i = 0; i < words.length; i++) {

            char[] array = words[i].toCharArray(); // сождаем массив символов из строки
            Arrays.sort(array); // сортируем полученый массив по возрастанию
            words[i] = new String(array);// формируем из отсортированного массива строку и помещаем в массив строк
        }

        return String.join(" ", words[0], words[1]).toLowerCase(); // возвращаем собраную стороку
    }
}
