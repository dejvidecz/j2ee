package service;

import DAO.BasicDAO;
import DAO.BazarDAO;
import DAO.CarDAO;
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
import repository.BazarRepository;
import repository.CarRepository;
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
public class CarOfferServiceTest {

    @Inject
    CarService carService;

    @Inject
    CarRepository carRepository;

    private CarOffer createDefaultCar() {
        return TestHelpFactory.createCar(2001, Brand.AUDI, "Black", 20000, new Date(), "Prodej auta", "Popis", "12345", "asd@asd.com", "12312312312");
    }

    @Test
    public void test1create() throws Exception {
        CarOffer c1 = createDefaultCar();
        carService.create(c1);
        assertNotNull(c1.getId());
    }

    @Test
    public void test2read() throws Exception {

        CarOffer c1 = createDefaultCar();
        carService.create(c1);
        CarOffer fromRepo = carRepository.findById(c1.getId());
        assertEquals(c1.getTitle(), fromRepo.getTitle());
    }

    @Test
    public void test3update() throws Exception {
        CarOffer c1 = createDefaultCar();
        carService.create(c1);
        c1.setTitle("Upraveno");
        carService.update(c1);
        assertEquals("Upraveno", carRepository.findById(c1.getId()).getTitle());

    }

    @Test
    public void test4delete() throws Exception {
        CarOffer c1 = createDefaultCar();
        carService.create(c1);
        carService.delete(c1);
        assertNull(carRepository.findById(c1.getId()));
    }


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(CarService.class, EntityManager.class, CarDAO.class, CarOffer.class, VehicleOffer.class, BasicDAO.class, Resources.class, CarRepository.class, VehicleViewVisitorInterface.class, VehicleViewVisitor.class, User.class, Brand.class, CarType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                ;
    }

}
