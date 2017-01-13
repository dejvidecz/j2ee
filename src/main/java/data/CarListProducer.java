package data;

import model.CarOffer;
import repository.CarRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Dejv on 10.01.17.
 */
public class CarListProducer {

    @Inject
    private CarRepository carRepository;

    public List<CarOffer> carOfferList;

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        carOfferList = carRepository.findAll();
    }

    @Produces
    @Named
    public List<CarOffer> getCarOfferList(){
        return carOfferList;
    }
}
