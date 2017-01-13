package service;

import DAO.MotoDAO;
import model.CarOffer;
import model.MotoOffer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Dejv on 10.01.17.
 */
@Stateless //always transactions
public class MotoService implements Serializable {

    @Inject
    private transient MotoDAO motoDAO;


    public MotoOffer create(MotoOffer entity) {
        motoDAO.create(entity);
        return entity;
    }


    public void delete(CarOffer bazar) {
        motoDAO.delete(bazar);
    }

    public void update(CarOffer bazar) {
        motoDAO.update(bazar);
    }
}
