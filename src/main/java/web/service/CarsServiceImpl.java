package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarsDAO;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarsServiceImpl implements CarsService {

    private static List<Car> ALL_CARS;

    @Autowired
    private CarsDAO  carsDAO;

    @Override
    public List<Car> getCars(int count) {

        if(ALL_CARS == null) {
            ALL_CARS = new ArrayList<>();
            ALL_CARS.addAll(carsDAO.getCars());
        }
        return ALL_CARS.stream().limit(count).toList();
    }
}
