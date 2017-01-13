package data;

import model.VehicleOffer;
import model.VehicleOffer;
import repository.VehicleRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Dejv on 10.01.17.
 */
public class VehicleListProducer {

    @Inject
    private VehicleRepository vehicleRepository;

    public List<VehicleOffer> vehicleOfferList;

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        vehicleOfferList = vehicleRepository.findAll();
    }

    @Produces
    @Named
    public List<VehicleOffer> getVehicleOfferList(){
        return vehicleOfferList;
    }
}
