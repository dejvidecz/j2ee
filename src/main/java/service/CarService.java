package service;

import DAO.CarDAO;
import model.CarOffer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Dejv on 10.01.17.
 */
@Stateless //always transactions
public class CarService implements Serializable {

    @Inject
    private transient CarDAO carDAO;

    /*
    public CarOffer create(CarOffer car){
        return carDAO.create(car);
    }
*/

    public <T> T create(T entity) {
        carDAO.create(entity);
        //entityManager.persist(entity);
        //entityManager.merge(entity);
        return entity;
    }


    public void delete(CarOffer bazar) {
        carDAO.delete(bazar);
    }

    public void update(CarOffer bazar) {
        carDAO.update(bazar);
    }
}
