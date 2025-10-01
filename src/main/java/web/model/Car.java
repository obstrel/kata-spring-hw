package web.model;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private static List<Car> ALL_CARS = null;
    private String model;

    private String make;

    private int year;



    public Car() {
    }

    public Car(String model, String make, int year) {
        this.model = model;
        this.make = make;
        this.year = year;
    }

    public static List<Car> getCars(int count) {
        if(ALL_CARS == null) {
            ALL_CARS = new ArrayList<>();
            ALL_CARS.add(new Car("mod1", "make", 1900));
            ALL_CARS.add(new Car("mod2", "make2", 1800));
            ALL_CARS.add(new Car("mod3", "make3", 1700));
            ALL_CARS.add(new Car("mod4", "make4", 1600));
            ALL_CARS.add(new Car("mod5", "make5", 1500));
        }
        return ALL_CARS.stream().limit(count).toList();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
