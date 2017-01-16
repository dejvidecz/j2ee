package service;

import DAO.BazarDAO;
import model.Bazar;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dejv on 10.01.17.
 */
@Stateless
public class BazarService {


    @Inject
    private transient BazarDAO bazarDAO;


    public Bazar create(Bazar bazar){
        return bazarDAO.create(bazar);
    }


    public void delete(Bazar bazar){
        bazarDAO.delete(bazar);
    }

    public void update(Bazar bazar){
        bazarDAO.update(bazar);
    }

}
