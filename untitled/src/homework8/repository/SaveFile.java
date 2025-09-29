package homework8.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaveFile {
    private final File file = new File("C:\\Users\\Анастасия\\dev\\Educational-project\\untitled\\src\\homework8\\data\\FileSave.txt");;


    public void save(String string) { // записываем в файл

        try (Writer fileWriter = new FileWriter(file, true)) {
            fileWriter.write(string);


        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
}
