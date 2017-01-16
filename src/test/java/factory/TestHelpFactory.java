package factory;

import model.Brand;
import model.CarOffer;
import model.MotoOffer;

import java.util.Date;

/**
 * Created by Dejv on 16.01.17.
 */
public class TestHelpFactory {


    public static CarOffer createCar(int year, Brand brand, String color, double sellPrice, Date date_added, String title, String description, String zip, String email, String phoneNumber) {
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
    public static MotoOffer createMoto(int year, Brand brand, String color, double sellPrice, Date date_added, String title, String description, String zip, String email, String phoneNumber) {
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



}
