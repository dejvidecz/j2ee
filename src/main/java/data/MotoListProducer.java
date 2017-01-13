package data;

import model.MotoOffer;
import repository.MotoRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Dejv on 10.01.17.
 */
public class MotoListProducer {

    @Inject
    private MotoRepository motoRepository;

    public List<MotoOffer> motoOfferList;

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        motoOfferList = motoRepository.findAll();
    }

    @Produces
    @Named
    public List<MotoOffer> getMotoOfferList(){
        return motoOfferList;
    }
}
