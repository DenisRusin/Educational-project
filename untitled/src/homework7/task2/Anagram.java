package homework7.task2;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

    public boolean checkAnagram(String str1, String str2) {

        char[] symbols1 = str1.toLowerCase().toCharArray();
        char[] symbols2 = str2.toLowerCase().toCharArray();

        Map<Character, Integer> words1 = new HashMap<>(countingCharacter(symbols1)); // создаем мапу символов
        Map<Character, Integer> words2 = new HashMap<>(countingCharacter(symbols2)); // ключ - символ, значение - кол-во повторов

        return words1.equals(words2); // сравниваем мапы
    }

    public Map<Character, Integer> countingCharacter(char[] symbols) {

        Map<Character, Integer> words = new HashMap<>();

        for (Character symbol : symbols) {
            int counter = 1;
            if (words.containsKey(symbol)) {
                counter = words.get(symbol);
                counter++;
            }
            words.put(symbol, counter);
        }

        return words;
    }
}
