package controller.vehicle;

import helpers.FrontendUrlHelper;
import model.MotoOffer;
import service.MotoService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * Created by Dejv on 10.01.17.
 */
@Model
@SessionScoped
public class MotoController implements Serializable {

    @Produces
    @Named
    private MotoOffer motoOffer;

    @Inject
    private MotoService motoService;

    @PostConstruct
    public void init() throws NamingException, JMSException {
        motoOffer = new MotoOffer();
    }

    public String register() throws NamingException, JMSException {
        motoOffer.setDate_added(new Date());
        motoService.create(motoOffer);
        init();
        return FrontendUrlHelper.MOTO_LIST;
    }

    public String detail(MotoOffer motoOffer){
        return "detail/motoOffer";
    }

}
