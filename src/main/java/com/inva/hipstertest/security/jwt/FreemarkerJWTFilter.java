package com.inva.hipstertest.security.jwt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inva.hipstertest.service.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
public class FreemarkerJWTFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(com.inva.hipstertest.security.jwt.FreemarkerJWTFilter.class);

    private TokenProvider tokenProvider;

    public FreemarkerJWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            // try to get token from header
            String jwt = resolveTokenFromHeader(httpServletRequest);
            // try to get token from cookie
            if (jwt == null || jwt.isEmpty()) {
                jwt = resolveTokenFromCookie(httpServletRequest);
            }
            if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
                Authentication authentication = this.tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ExpiredJwtException eje) {
            log.info("Security exception for user {} - {}",
                eje.getClaims().getSubject(), eje.getMessage());

            log.trace("Security exception trace: {}", eje);
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String resolveTokenFromCookie(HttpServletRequest request){
        String bearerToken = CookieUtil.getValue(request, "JWT-TOKEN");
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

    private String resolveTokenFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
