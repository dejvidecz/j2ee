package controller;

import model.Car;
import service.CarService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dejv on 10.01.17.
 */
@Model
@SessionScoped
public class CarController implements Serializable {

    @Produces
    @Named
    private Car car;

    @Inject
    private CarService carService;

    @PostConstruct
    public void init(){
        car = new Car();
        car.setDate_accepted(new Date());

    }

    public String register(){
        carService.create(car);
        init();
        return  "add";
    }

}
