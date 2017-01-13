package helpers;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dejv on 13.01.17.
 */
@ApplicationScoped
@Model
public class FrontendUrlHelper {

    public static final String INDEX_PAGE = "/frontend/main.xhtml";
    public static final String VEHICLE_LIST = "/frontend/vehicle/list.xhtml";
    public static final String CAR_LIST = "/frontend/car/list.xhtml";
    public static final String MOTO_LIST = "/frontend/moto/list.xhtml";

    public static final String VEHICLE_ADD = "/frontend/member/vehicle/add.xhtml";
    public static final String CAR_ADD = "/frontend/member/car/add.xhtml";
    public static final String MOTO_ADD = "/frontend/member/moto/add.xhtml";


    public String getBase(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public String getIndexPage() {
        return getBase()+INDEX_PAGE;
    }

    public String getVehicleList() {
        return getBase()+VEHICLE_LIST;
    }

    public String getCarList() {
        return getBase()+CAR_LIST;
    }

    public String getMotoList() {
        return getBase()+MOTO_LIST;
    }

    public String getVehicleAdd() {
        return getBase()+VEHICLE_ADD;
    }

    public String getCarAdd() {
        return getBase()+CAR_ADD;
    }

    public String getMotoAdd() {
        return getBase()+MOTO_ADD;
    }
}
