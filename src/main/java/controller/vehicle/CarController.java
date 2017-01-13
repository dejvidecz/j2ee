package controller.vehicle;

import helpers.FrontendUrlHelper;
import model.CarOffer;
import service.CarService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
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
    private CarOffer carOffer;

    @Inject
    private CarService carService;

    @PostConstruct
    public void init() throws NamingException, JMSException {
        carOffer = new CarOffer();
    }

    public String register() throws NamingException, JMSException {
        carOffer.setDate_added(new Date());
        carService.create(carOffer);
        init();
        return FrontendUrlHelper.CAR_LIST;
    }


    public String detail(CarOffer carOffer){
        this.carOffer = carOffer;
        return "/frontend/carOffer/detail";
    }

}
