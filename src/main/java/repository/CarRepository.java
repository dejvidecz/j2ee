package repository;

import model.CarOffer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Dejv on 10.01.17.
 */
@ApplicationScoped
public class CarRepository {

    @Inject
    private EntityManager entityManager;

    public CarOffer findById(long id) {
        return entityManager.find(CarOffer.class, id);
    }

    public List<CarOffer> findAll() {
        return entityManager.createQuery("select c from CarOffer c").getResultList();
    }

    public CarOffer finByTitle(String title) {
        return (CarOffer) entityManager.createQuery("select c from CarOffer c WHERE c.title = :title").setParameter("title", title).getSingleResult();
    }
}
