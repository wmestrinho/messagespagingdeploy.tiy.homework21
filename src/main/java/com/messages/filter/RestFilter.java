package com.messages.filter;

import com.messages.secure.TokenMaster;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WagnerMestrinho on 3/9/17.
 */
@WebFilter(filterName = "RestFilter")
public class RestFilter implements Filter{
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            // we need to look for authorization token and validate it
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            HttpServletRequest req = (HttpServletRequest) servletRequest;

            // check for key based authentication
            boolean authorized = false;
            String authToken = req.getHeader("x-authorization-key");
            TokenMaster tm = new TokenMaster();
            if(authToken != null && tm.validate(authToken)){
                // send request on its way like normal
                filterChain.doFilter(req, res);
            }else{
                res.getWriter().write("Invalid Authorization. No No. Go Away.");
            }

        }

        @Override
        public void destroy() {

        }
    }

