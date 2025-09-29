package homework8.test;

import homework8.model.Car;
import homework8.repository.LoadingFile;
import homework8.repository.Statistics;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LoadingFile loadingFile = new LoadingFile();
        ArrayList<Car> cars = loadingFile.loadFromFile();
        Statistics statistics = new Statistics();


        statistics.printStatistic(cars);
        statistics.searchForCarsByColor(cars, "Black", 0);
        statistics.getUniqueCar(cars, 700000, 800000);
        statistics.getColorCarMinCost(cars);
        statistics.getAvgModel(cars, "Toyota");
        statistics.getAvgModel(cars, "Volvo");
    }

}
