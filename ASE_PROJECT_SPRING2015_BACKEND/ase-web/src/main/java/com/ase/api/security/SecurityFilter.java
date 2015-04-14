package com.ase.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: SrilakshmiNowpada
 * Date: 7/10/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityFilter extends OncePerRequestFilter {
    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityFilter.class.getName());
    StringWriter stack = new StringWriter();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }
}
