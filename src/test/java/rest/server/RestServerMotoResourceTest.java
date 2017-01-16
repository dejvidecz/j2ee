package rest.server;

import DAO.BasicDAO;
import DAO.CarDAO;
import DAO.MotoDAO;
import model.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.extension.rest.client.Header;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import repository.CarRepository;
import repository.MotoRepository;
import rest.CarResourceRESTService;
import rest.MotoResourceRESTService;
import service.CarService;
import service.MotoService;
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
public class RestServerMotoResourceTest {


    @Inject
    EntityManager entityManager;

    @Inject
    MotoService motoService;

    @Inject
    MotoRepository motoRepository;


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
    private MotoOffer createMoto(int year, Brand brand, String color, double sellPrice, Date date_added, String title, String description, String zip, String email, String phoneNumber) {
        MotoOffer motoOffer = new MotoOffer();
        motoOffer.setYear(year);
        motoOffer.setBrand(brand);
        motoOffer.setColor(color);
        motoOffer.setSellPrice(sellPrice);
        motoOffer.setDate_added(date_added);
        motoOffer.setTitle(title);
        motoOffer.setDescription(description);
        motoOffer.setZip(zip);
        motoOffer.setEmail(email);
        motoOffer.setPhoneNumber(phoneNumber);
        return motoOffer;
    }

    private MotoOffer createDefaultMoto() {
        return createMoto(2001, Brand.AUDI, "Black", 20000, new Date(), "Prodej auta", "Popis", "12345", "asd@asd.com", "12312312312");
    }


    @Test
    @Header(name = "Content-type", value = "application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void test1List(@ArquillianResteasyResource MotoResourceRESTService motoResourceRESTService) {
        motoService.create(createDefaultMoto());
        final List<MotoOffer> result = motoResourceRESTService.motoList();
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

    }

    @Test
    @Header(name = "Content-type", value = "application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void test2Detail(@ArquillianResteasyResource MotoResourceRESTService motoResourceRESTService) {
        MotoOffer motoOffer = createDefaultMoto();
        motoService.create(motoOffer);

        MotoOffer restObject = motoResourceRESTService.lookupMotoById(motoOffer.getId());
        Assert.assertNotNull(restObject);
        Assert.assertEquals(motoOffer.getId(), restObject.getId());

    }

    @Test
    @Header(name = "Content-type", value = "application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void test3Create(@ArquillianResteasyResource MotoResourceRESTService motoResourceRESTService) {
        MotoOffer motoOffer = createDefaultMoto();
        motoOffer.setTitle("Test1234");
        motoResourceRESTService.createMoto(motoOffer);

        MotoOffer motoOffer1 = motoRepository.finByTitle("Test1234");

        Assert.assertNotNull(motoOffer1);
        Assert.assertEquals(motoOffer.getId(), motoOffer1.getId());

    }


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(MotoOffer.class,MotoService.class,CarResourceRESTService.class, MotoDAO.class,MotoOffer.class,MotoResourceRESTService.class,MotoRepository.class, CarService.class, EntityManager.class, CarDAO.class, CarOffer.class, VehicleOffer.class, BasicDAO.class, Resources.class, CarRepository.class, VehicleViewVisitorInterface.class, VehicleViewVisitor.class, User.class, Brand.class, CarType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                ;
    }
}
