package batching;

import model.VehicleOffer;
import model.VehicleOffer;
import repository.VehicleRepository;
import service.CarService;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dejv on 12.01.17.
 */
@Named
public class ItemReader extends AbstractItemReader {

    private BufferedReader reader;

    @Inject
    private VehicleRepository vehicleRepository;

    private List<VehicleOffer> vehicleOfferList = new ArrayList<VehicleOffer>();

    private int index;

    public void open(Serializable checkpoint) throws Exception {
        System.out.println("opening");
        vehicleOfferList = vehicleRepository.findAll();
        index=0;
    }




    @Override
    public Object readItem() throws Exception {
        System.out.println("read index"+index);
        if(!vehicleOfferList.isEmpty() && (vehicleOfferList.size()-1) > index){
            return vehicleOfferList.get(index++);
        }else{
            return null;
        }

    }
}
