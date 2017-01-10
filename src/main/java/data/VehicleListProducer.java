package data;

import model.Vehicle;
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

    public List<Vehicle> vehicleList;

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        vehicleList = vehicleRepository.findAll();
    }

    @Produces
    @Named
    public List<Vehicle> getVehicleList(){
        return vehicleList;
    }
}
