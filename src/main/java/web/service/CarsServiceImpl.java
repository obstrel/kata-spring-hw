package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import web.dao.CarsDAO;
import web.dao.CarsDAOImpl;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarsServiceImpl implements CarsService {

    private static List<Car> ALL_CARS;

    @Autowired
    private CarsDAO  carsDAO;

    @Override
    public List<Car> getCars() {

        if(ALL_CARS == null) {
            ALL_CARS = new ArrayList<>();
            ALL_CARS.addAll(carsDAO.getCars());
        }

        return ALL_CARS;
    }
}
