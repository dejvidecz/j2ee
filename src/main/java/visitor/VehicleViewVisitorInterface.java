package visitor;

import model.CarOffer;
import model.MotoOffer;

/**
 * Created by Dejv on 13.01.17.
 */
public interface VehicleViewVisitorInterface {
    String visit(CarOffer carOffer);
    String visic(MotoOffer motoOffer);

}
