package controller;

import helpers.AdminUrlHelper;
import model.User;
import org.picketlink.authorization.annotations.RolesAllowed;
import rbac.ApplicationRole;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Dejv on 12.01.17.
 */
@SessionScoped
@Model
public class UserController implements Serializable{

    @Produces
    @Named
    private User user;

    @Inject
    private UserService userService;


    private String originalURL = "";


    @PostConstruct
    public void init(){

        this.user = new User();

    }


    public void login() throws IOException {

        if(userService.login(user)){
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            if(externalContext.getSessionMap().containsKey("originalURL")) {
                this.originalURL = (String)externalContext.getSessionMap().get("originalURL");
            } else {
                this.originalURL = externalContext.getRequestContextPath() + AdminUrlHelper.INDEX_PAGE;
            }
            FacesContext context = FacesContext.getCurrentInstance();

            externalContext.redirect(this.originalURL);
        }



    }

    public void logout(){
        userService.logout();
    }
}
