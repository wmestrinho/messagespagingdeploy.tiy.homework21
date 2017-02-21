package com.messages.filter;

import com.messages.data.Author;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WagnerMestrinho on 2/12/17.
 */
@WebFilter(filterName = "Authorize")
public class Authorize implements Filter {
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse resp = ((HttpServletResponse) response);
        // check session
        Author usr = (Author) req.getSession().getAttribute("user");
        boolean authorized = (usr != null);
        boolean tryingToLogin = req.getRequestURI().endsWith("/open/login.jsp")||req.getRequestURI().endsWith("authenticate");
        if(authorized || tryingToLogin) {
            chain.doFilter(request, response);
        }else{
            resp.sendRedirect("/open/login.jsp");
        }
    }


    public void init(FilterConfig config) throws ServletException {

    }




}
