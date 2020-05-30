package com.tokyo.supermix.security;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.tokyo.supermix.data.repositories.auth.UserRepository;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
@Autowired(required = true)
UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String username = request.getHeader("X-User-Header");
            if(username != null) {
              UserDetails userDetails = UserPrincipal.create(userRepository.findByUserName(username));
              setAuth(userDetails,request);
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
}

