package com.study.library.security.filter;

import com.study.library.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends GenericFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Boolean isPermitAll = (Boolean) request.getAttribute("isPermitAll");

        if(!isPermitAll) {
            String accessToken = request.getHeader("Authorization");
            String removedBearerToken = jwtProvider.removeBearer(accessToken);
            Claims claims = null;

            try {
                claims = jwtProvider.getClaims(removedBearerToken);
            } catch (Exception e) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);    // 401 에러( 인증 실패 )
//                response.sendError(401);    // 401 에러( 인증 실패 )
                response.sendError(HttpStatus.UNAUTHORIZED.value());    // 401 에러( 인증 실패 )
                return;
            }

            Authentication authentication = jwtProvider.getAuthentication(claims);

            if(authentication == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());    // 401 에러( 인증 실패 )
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 전처리
        filterChain.doFilter(request, response);    // doFilter 기준 -전처리 후처리
        // 후처리
    }

}
