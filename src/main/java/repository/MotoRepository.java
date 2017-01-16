package repository;

import model.CarOffer;
import model.MotoOffer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Dejv on 10.01.17.
 */
@ApplicationScoped
public class MotoRepository {

    @Inject
    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    public MotoOffer findById(long id){
        return entityManager.find(MotoOffer.class,id);
    }

    public List<MotoOffer> findAll() {
        return entityManager.createQuery("select m from MotoOffer m").getResultList();
    }

    public MotoOffer finByTitle(String title) {
        return (MotoOffer) entityManager.createQuery("select c from MotoOffer c WHERE c.title = :title").setParameter("title",title).getSingleResult();

    }
}
