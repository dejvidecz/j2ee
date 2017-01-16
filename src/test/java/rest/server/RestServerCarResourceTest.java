package rest.server;

import DAO.BasicDAO;
import DAO.CarDAO;
import model.Brand;
import model.CarOffer;
import model.User;
import model.VehicleOffer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.extension.rest.client.Header;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import repository.CarRepository;
import rest.CarResourceRESTService;
import service.CarService;
import util.Resources;
import visitor.VehicleViewVisitor;
import visitor.VehicleViewVisitorInterface;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created by Dejv on 16.01.17.
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestServerCarResourceTest {


    @Inject
    EntityManager entityManager;

    @Inject
    CarService carService;

    @Inject
    CarRepository carRepository;


    /**
     * Just for better creating in test
     *
     * @param year
     * @param brand
     * @param color
     * @param sellPrice
     * @param date_added
     * @param title
     * @param description
     * @param zip
     * @param email
     * @param phoneNumber
     * @return
     */
    private CarOffer createCar(int year, Brand brand, String color, double sellPrice, Date date_added, String title, String description, String zip, String email, String phoneNumber) {
        CarOffer carOffer = new CarOffer();
        carOffer.setYear(year);
        carOffer.setBrand(brand);
        carOffer.setColor(color);
        carOffer.setSellPrice(sellPrice);
        carOffer.setDate_added(date_added);
        carOffer.setTitle(title);
        carOffer.setDescription(description);
        carOffer.setZip(zip);
        carOffer.setEmail(email);
        carOffer.setPhoneNumber(phoneNumber);
        return carOffer;
    }

    private CarOffer createDefaultCar() {
        return createCar(2001, Brand.AUDI, "Black", 20000, new Date(), "Prodej auta", "Popis", "12345", "asd@asd.com", "12312312312");
    }


    @Test
    @Header(name = "Content-type", value = "application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void test1List(@ArquillianResteasyResource CarResourceRESTService carResourceRESTService) {
        carService.create(createDefaultCar());
        final List<CarOffer> result = carResourceRESTService.carList();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

    }

    @Test
    @Header(name = "Content-type", value = "application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void test2Detail(@ArquillianResteasyResource CarResourceRESTService carResourceRESTService) {
        CarOffer carOffer = createDefaultCar();
        carService.create(carOffer);

        CarOffer restObject = carResourceRESTService.lookupCarById(carOffer.getId());
        Assert.assertNotNull(restObject);
        Assert.assertEquals(carOffer.getId(), restObject.getId());

    }

    @Test
    @Header(name = "Content-type", value = "application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void test3Create(@ArquillianResteasyResource CarResourceRESTService carResourceRESTService) {
        CarOffer carOffer = createDefaultCar();
        carOffer.setTitle("Test1234");
        carResourceRESTService.createCar(carOffer);

        CarOffer carOffer1 = carRepository.finByTitle("Test1234");

        Assert.assertNotNull(carOffer1);
        Assert.assertEquals(carOffer.getId(), carOffer1.getId());

    }


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(CarResourceRESTService.class, CarService.class, EntityManager.class, CarDAO.class, CarOffer.class, VehicleOffer.class, BasicDAO.class, Resources.class, CarRepository.class, VehicleViewVisitorInterface.class, VehicleViewVisitor.class, User.class, Brand.class, CarOffer.CarType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                ;
    }
}
