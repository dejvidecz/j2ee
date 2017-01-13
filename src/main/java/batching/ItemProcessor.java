package batching;

import model.VehicleOffer;
import model.VehicleOffer;

import javax.inject.Named;

/**
 * Created by Dejv on 12.01.17.
 */
@Named
public class ItemProcessor implements javax.batch.api.chunk.ItemProcessor {

    @Override
    public VehicleOffer processItem(Object t) {
       return (VehicleOffer) t;
    }

}
