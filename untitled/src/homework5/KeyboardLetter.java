package homework5;

public class KeyboardLetter {

    static String keyboard = "qwertyuiopasdfghjklzxcvbnm";


    public void getPreviousLetter(String letter) {
        int index = keyboard.indexOf(letter);

        if (index == -1) {
            System.out.println("Такой буквы в английской раскладке клавиатуры нет");
        } else if (index == 0) {
            System.out.println("Cлева стоящая буква: " + keyboard.charAt(keyboard.length() - 1));

        } else {
            System.out.println("Cлева стоящая буква: " + keyboard.charAt(index + -1));
        }
    }
}
