package webfilter;

import helpers.AdminUrlHelper;
import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;
import org.picketlink.Identity;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * Created by Dejv on 12.01.17.
 */
@WebFilter("/admin/*")
public class AdminAuthorizationFilter extends HttpFilter {

    @Inject
    private Identity identity;


    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession, FilterChain filterChain) throws ServletException, IOException {
        String loginURL = httpServletRequest.getContextPath() + AdminUrlHelper.LOGIN_PAGE;


        boolean loggedIn = identity.isLoggedIn();
        boolean loginRequest = httpServletRequest.getRequestURI().equals(loginURL);
        boolean resourceRequest = Servlets.isFacesResourceRequest(httpServletRequest);

        String originalUrl = httpServletRequest.getRequestURL().toString();


        if (loggedIn || loginRequest || resourceRequest) {
            if (!resourceRequest) { // Prevent browser from caching restricted resources. See also http://stackoverflow.com/q/4194207/157882
                Servlets.setNoCacheHeaders(httpServletResponse);
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse); // So, just continue request.
        }
        else {
            httpServletRequest.getSession().setAttribute("originalURL", originalUrl);

            Servlets.facesRedirect(httpServletRequest, httpServletResponse, loginURL);
        }
    }
}
