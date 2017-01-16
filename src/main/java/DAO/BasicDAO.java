package DAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Basic DAO for access to database
 *
 * @author Dejv
 */
@Transactional
public class BasicDAO implements Serializable{

    /**
     * Entity manager
     */
    @Inject
    private EntityManager entityManager;

    public BasicDAO() {
        super();
    }


    public <T> T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public <T> T update(T entity) {
        entityManager.merge(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        return entity;
    }

    public <T> void delete(T entity){
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return entityManager.find(entityClass, primaryKey);
    }


}
