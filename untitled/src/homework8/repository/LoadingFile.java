package homework8.repository;

import homework8.model.Car;

import java.io.*;
import java.util.ArrayList;

public class LoadingFile {
    private final File file = new File("C:\\Users\\Анастасия\\dev\\Educational-project\\untitled\\src\\homework8\\data\\FileRead.txt");


    public ArrayList<Car> loadFromFile() { // читаем файл

        ArrayList<String> history = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();

        try (Reader fileReader = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fileReader);

            while (br.ready()) {
                String line = br.readLine();
                history.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }

        if (history.size() <= 1) { // если файл пустой или есть только заголовок
            return cars;
        }


        for (int i = 1; i < history.size(); i++) {
            Car newCar = fromString(history.get(i));
            cars.add(newCar);
        }
        return cars;
    }


    private static Car fromString(String value) { // создаем обьеты Car
        String[] str = value.split("\\|");

        return new Car(str[0], str[1], str[2], Integer.parseInt(str[3]), Long.parseLong(str[4]));

    }
}
