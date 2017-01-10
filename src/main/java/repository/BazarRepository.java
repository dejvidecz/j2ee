package repository;

import model.Bazar;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by Dejv on 10.01.17.
 */
@ApplicationScoped
public class BazarRepository {

    @Inject
    private EntityManager entityManager;

    public Bazar findById(long id){
        return entityManager.find(Bazar.class,id);
    }

}
