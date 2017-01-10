package controller;

import model.Bazar;
import repository.BazarRepository;
import service.BazarService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * Created by Dejv on 10.01.17.
 */
@Model
@ManagedBean(name = "bazarController")
public class BazarController {

    @Inject
    private FacesContext facesContext;

    @Produces
    @Named
    private Bazar bazar;

    @Inject
    private BazarService bazarService;

    @Inject
    private BazarRepository bazarRepository;

    @PostConstruct
    public void initNewBazar(){
        this.bazar = new Bazar();
    }

}
