package web.controller;

import data.CarsDAO;
import data.CarsDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    private static List<Car> ALL_CARS;
    private CarsDAO carsDAO = new CarsDAOImpl();

    @GetMapping(value = "/cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count, ModelMap model) {
        model.addAttribute("cars", getCars(count == null ? Integer.MAX_VALUE : count));
        return "cars";
    }

    public List<Car> getCars(int count) {
        if(ALL_CARS == null) {
            ALL_CARS = new ArrayList<>();
            ALL_CARS.addAll(carsDAO.getCars());
        }
        return ALL_CARS.stream().limit(count).toList();
    }


}
