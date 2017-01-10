package repository;

import model.Bazar;
import model.Vehicle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Dejv on 10.01.17.
 */
@ApplicationScoped
public class VehicleRepository {

    @Inject
    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    public Vehicle findById(long id){
        return entityManager.find(Vehicle.class,id);
    }

    public List<Vehicle> findAll() {
        return entityManager.createQuery("select v from Vehicle v").getResultList();
    }
}
