package service;

import DAO.BasicDAO;
import DAO.BazarDAO;
import model.Bazar;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import repository.BazarRepository;
import util.Resources;


import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.junit.Assert.*;


/**
 *
 * Created by Dejv on 10.01.17.
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BazarServiceTest {

    @Inject
    EntityManager entityManager;

    @Inject
    BazarService bazarService;

    @Inject
    BazarRepository bazarRepository;


    @Test
    public void test1create() throws Exception {
        System.out.println("Necum");
        Bazar b = new Bazar();
        b.setName("Test");
        bazarService.create(b);
        assertNotNull(b);

    }

    @Test
    public void test2update() throws Exception {
        Bazar b = new Bazar();
        b.setName("Test");
        bazarService.create(b);
        b.setName("test222");
        bazarService.update(b);
        assertEquals("test222", bazarRepository.findById(b.getId()).getName());

    }

    @Test
    public void test3delete() throws Exception {
        Bazar b = new Bazar();
        b.setName("Test");
        bazarService.create(b);
        bazarService.delete(b);
        assertNull(bazarRepository.findById(b.getId()));
    }


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(BazarService.class, EntityManager.class, BazarDAO.class, Bazar.class, BasicDAO.class, Resources.class, BazarRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                ;
    }

}
