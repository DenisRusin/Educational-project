package homework5;

public class Arrow {

    public int getCountArrow(String text) {
        StringBuilder str = new StringBuilder(text);
        String arrow1 = ">>-->";
        String arrow2 = "<--<<";
        int count = 0;

        while (str.indexOf(arrow1) != -1) { // считаем >>-->
            count++;
            str.replace(str.indexOf(arrow1), str.indexOf(arrow1) + arrow1.length(), "!!!!!");//заменяем стрелку
        } //на !!!!!, если удалять стрелку то могут появиться другие и подсчет будет не точен.


        while (str.indexOf(arrow2) != -1) { // считаем <--<<
            count++;
            str.replace(str.indexOf(arrow2), str.indexOf(arrow2) + arrow2.length(), "!!!!!");
        }
        return count;
    }
}
