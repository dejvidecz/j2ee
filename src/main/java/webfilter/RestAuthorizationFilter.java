package webfilter;

import org.omnifaces.filter.HttpFilter;
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
@WebFilter("/rest/*")
public class RestAuthorizationFilter extends HttpFilter {

    private String token = "admin";

    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession, FilterChain filterChain) throws ServletException, IOException {


        String token = httpServletRequest.getParameter("token");
        if(!this.token.equals(token)){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("Bad token");
            httpServletResponse.getWriter().write("Unauthorized");
        }else{
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }

}
