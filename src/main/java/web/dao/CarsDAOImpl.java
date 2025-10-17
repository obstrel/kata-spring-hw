package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.List;

@Repository
public class CarsDAOImpl implements CarsDAO {
    @Override
    public List<Car> getCars() {
        return List.of(new Car("mod1", "make", 1900)
        , new Car("mod2", "make2", 1800)
        , new Car("mod3", "make3", 1700)
        , new Car("mod4", "make4", 1600)
        , new Car("mod5", "make5", 1500));
    }
}
