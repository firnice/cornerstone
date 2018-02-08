package com.cornerstone.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cornerstone.TokenAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author: liyl
 * @date: 2018/2/7 上午11:21
 * @since 1.0.0
 */
public class JWTAuthenticationFilter  extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
        throws IOException, ServletException {
        Authentication authentication = TokenAuthenticationService
            .getAuthentication((HttpServletRequest)request);

        SecurityContextHolder.getContext()
            .setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }

}
