package service;

import DAO.CarDAO;
import model.CarOffer;
import repository.CarRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Dejv on 10.01.17.
 */
@Stateless
public class CarService implements Serializable {

    @Inject
    private transient CarDAO carDAO;

    public CarOffer create(CarOffer entity) {
        return carDAO.create(entity);
    }

    public void delete(CarOffer bazar) {
        carDAO.delete(bazar);
    }

    public void update(CarOffer bazar) {
        carDAO.update(bazar);
    }


}
