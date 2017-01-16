package service;

import DAO.BasicDAO;

import DAO.MotoDAO;
import factory.TestHelpFactory;
import model.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import repository.MotoRepository;
import util.Resources;
import visitor.VehicleViewVisitor;
import visitor.VehicleViewVisitorInterface;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;

import static org.junit.Assert.*;


/**
 * Created by Dejv on 10.01.17.
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MotoOfferServiceTest {

    @Inject
    MotoService motoService;

    @Inject
    MotoRepository motoRepository;



    private MotoOffer createDefaultMoto() {
        return TestHelpFactory.createMoto(2001, Brand.AUDI, "Black", 20000, new Date(), "Prodej auta", "Popis", "12345", "asd@asd.com", "12312312312");
    }


    @Test
    public void test1create() throws Exception {
        MotoOffer m1 = createDefaultMoto();
        motoService.create(m1);
        assertNotNull(m1.getId());
    }

    @Test
    public void test2read() throws Exception {

        MotoOffer m1 = createDefaultMoto();
        motoService.create(m1);
        MotoOffer fromRepo = motoRepository.findById(m1.getId());
        assertEquals(m1.getTitle(), fromRepo.getTitle());
    }

    @Test
    public void test3update() throws Exception {
        MotoOffer m1 = createDefaultMoto();
        motoService.create(m1);
        m1.setTitle("Upraveno");
        motoService.update(m1);
        assertEquals("Upraveno", motoRepository.findById(m1.getId()).getTitle());

    }

    @Test
    public void test4delete() throws Exception {
        MotoOffer m1 = createDefaultMoto();
        motoService.create(m1);
        motoService.delete(m1);
        assertNull(motoRepository.findById(m1.getId()));
    }



    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(MotoService.class, EntityManager.class, MotoDAO.class, MotoOffer.class, VehicleOffer.class, BasicDAO.class, Resources.class, MotoRepository.class, VehicleViewVisitorInterface.class, VehicleViewVisitor.class, User.class, Brand.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                ;
    }

}
