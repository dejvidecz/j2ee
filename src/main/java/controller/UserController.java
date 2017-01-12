package controller;

import helpers.AdminUrlHelper;
import jms.Email;
import jms.EmailJsmSender;
import model.User;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * Created by Dejv on 12.01.17.
 */
@SessionScoped
@Model
public class UserController implements Serializable {

    @Produces
    @Named
    private User user;

    @Inject
    private UserService userService;


    private String originalURL = "";


    @PostConstruct
    public void init() {

        this.user = new User();

    }


    public void login() throws IOException, NamingException, JMSException {

        //EmailJsmSender emailJsmSender = new EmailJsmSender();
        //emailJsmSender.send(new Email("rece","asdasdadads"));


        if (userService.login(user)) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            if (externalContext.getSessionMap().containsKey("originalURL")) {
                this.originalURL = (String) externalContext.getSessionMap().get("originalURL");
            } else {
                this.originalURL = externalContext.getRequestContextPath() + AdminUrlHelper.INDEX_PAGE;
            }
            FacesContext context = FacesContext.getCurrentInstance();

            externalContext.redirect(this.originalURL);
        }


    }

    public void logout() {
        userService.logout();
    }
}
