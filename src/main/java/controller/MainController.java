package controller;

import javax.enterprise.inject.Model;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Dejv on 12.01.17.
 */
@Model
public class MainController {


    @Path("/")
    @GET
    public String mvc(){
        return "/mains.xhtml";
    }



}
