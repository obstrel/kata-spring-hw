package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.CarsDAO;
import web.dao.CarsDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarsService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarsService carsService;

    @GetMapping(value = "/cars")
    public String getCars(@RequestParam(value = "count", required = false) Integer count, ModelMap model) {
        model.addAttribute("cars", carsService.getCars(count == null ? Integer.MAX_VALUE : count));
        return "cars";
    }

}
