package com.auth.security.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth.security.service.AuthUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private JwtTokenProvider tokenProvider;
  @Autowired
  private AuthUserDetailsService authUserDetailsService;
  
  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);
      String username = request.getHeader("X-User-Header");
      if(username != null) {
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);
        setAuth(userDetails,request);
      }
      if(jwt != null ) {     
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
          Long userId = tokenProvider.getUserIdFromJWT(jwt);
          UserDetails userDetails = authUserDetailsService.loadUserById(userId);
          setAuth(userDetails,request);
      }
      }
      
    } catch (Exception ex) {
      logger.error("Could not set user authentication in security context", ex);
    }
    filterChain.doFilter(request, response);
  }
  
  private void setAuth(UserDetails userDetails,HttpServletRequest request) {
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
  
  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }
}
