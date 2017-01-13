package visitor;

import model.CarOffer;
import model.MotoOffer;

import javax.inject.Named;

/**
 *
 * Created by Dejv on 13.01.17.
 */
@Named
public class VehicleViewVisitor implements VehicleViewVisitorInterface {
    @Override
    public String visit(CarOffer carOffer) {
        return carOffer.accept(this);
    }

    @Override
    public String visic(MotoOffer motoOffer) {
        return motoOffer.accept(this);
    }
}
