package homework8.repository;

import homework8.model.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Statistics {

    SaveFile saveFile = new SaveFile();


    public void getColorCarMinCost(ArrayList<Car> cars) { // Вывести цвет автомобиля с минимальной стоимостью
        Optional<Car> carMinPrice = cars.stream().min(Comparator.comparingLong(Car::getCost));

        if (carMinPrice.isPresent()) {
            System.out.println("Цвет автомобиля с минимальной стоимостью: " + carMinPrice.get().getColor());
            saveFile.save("Цвет автомобиля с минимальной стоимостью: " + carMinPrice.get().getColor() + "\n");
        } else {
            System.out.println("В спиcке нет автомобилей");
        }
    }


    public void searchForCarsByColor(ArrayList<Car> cars, String colorToFind, int mileageToFind) { // Номера всех автомобилей, имеющих заданный в переменной цвет
        List<String> car = cars.stream().filter(c -> c.getColor().equals(colorToFind) || c.getMileage() == mileageToFind)// colorToFind или нулевой пробег mileageToFind
                .map(Car::getNumber).toList();


        System.out.println("Номера автомобилей по цвету или пробегу: " + String.join(" ", car));

        String save = "\n" + "Номера автомобилей по цвету или пробегу: " + String.join(" ", car) + "\n";
        saveFile.save(save);

    }


    public void getUniqueCar(ArrayList<Car> cars, int n, int m) { //Количество уникальных моделей в ценовом диапазоне от n до m тыс.
        int countCar = cars.stream().filter(c -> c.getCost() >= n && c.getCost() <= m)
                .map(Car::getModel).distinct().toList().size();

        System.out.println("Уникальные автомобили: " + countCar + " шт.");
        saveFile.save("Уникальные автомобили: " + countCar + " шт." + "\n");
    }


    public void getAvgModel(ArrayList<Car> cars, String modelToFind) { // Средняя стоимость искомой модели
        List<Car> carAvgPrice = cars.stream().filter(c -> c.getModel().equals(modelToFind)).toList();

        if (!carAvgPrice.isEmpty()) {
            System.out.println(String.format("Средняя стоимость модели %s: %.2f", modelToFind, carAvgPrice.stream()
                    .mapToLong(Car::getCost).average()
                    .getAsDouble()));
            saveFile.save(String.format("Средняя стоимость модели %s: %.2f%n", modelToFind, carAvgPrice.stream()
                    .mapToLong(Car::getCost).average()
                    .getAsDouble()));

        } else {
            System.out.println("Средняя стоимость модели " + modelToFind + ": 0.00");
            saveFile.save("Средняя стоимость модели " + modelToFind + ": 0.00" + "\n");
        }
    }

    public void printStatistic(ArrayList<Car> cars) { // печатаем список авто

        StringBuilder builder = new StringBuilder(String.format
                ("Автомобили в базе:%n%s %8s %8s %8s %8s%n", "Number", "Model", "Color", "Mileage", "Cost"));

        for (Car car : cars) {
            builder.append(String.format("%s %8s %8s %8s %8s%n", car.getNumber(), car.getModel(),
                    car.getColor(), car.getMileage(), car.getCost()));
        }

        String str = builder.toString();

        System.out.println(str);

        saveFile.save(str);
    }
}
