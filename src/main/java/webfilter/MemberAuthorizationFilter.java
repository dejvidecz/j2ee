package webfilter;

import helpers.AdminUrlHelper;
import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;
import org.picketlink.Identity;
import org.picketlink.authorization.util.AuthorizationUtil;
import org.picketlink.config.http.AuthorizationConfiguration;
import org.picketlink.config.http.PathConfiguration;
import org.picketlink.http.internal.authorization.AbstractPathAuthorizer;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;
import rbac.ApplicationRole;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dejv on 12.01.17.
 */
@WebFilter("/frontend/member/*")
public class MemberAuthorizationFilter extends HttpFilter {

    @Inject
    private Identity identity;

    @Inject
    private IdentityManager identityManager;

    @Inject
    private PartitionManager partitionManager;


    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession, FilterChain filterChain) throws ServletException, IOException {

        if (!AuthorizationUtil.hasRole(identity, partitionManager, ApplicationRole.MEMBER)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("Not acces level");
            httpServletResponse.getWriter().write("Unauthorized");
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}


