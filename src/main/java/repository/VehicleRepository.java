package repository;

import model.Bazar;
import model.VehicleOffer;
import model.VehicleOffer;

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

    public VehicleOffer findById(long id){
        return entityManager.find(VehicleOffer.class,id);
    }

    public List<VehicleOffer> findAll() {
        return entityManager.createQuery("select v from VehicleOffer v").getResultList();
    }
}
