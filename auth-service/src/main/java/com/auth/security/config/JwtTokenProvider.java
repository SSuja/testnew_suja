package com.auth.security.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.tokyo.supermix.data.dto.auth.PrivilegeRouteDto;
import com.tokyo.supermix.data.dto.auth.SubRouteDto;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.repositories.auth.MainRouteRepository;
import com.tokyo.supermix.data.repositories.auth.SubRouteRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
  @Autowired
  private MainRouteRepository mainRouteRepository;
  @Autowired
  private SubRouteRepository subRouteRepository;

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwtExpirationInMs}")
  private int jwtExpirationInMs;

  public String generateToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    Date now = new Date(); 
    System.out.println("user permssions "+ userPrincipal.getAuthorities().toString());
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
    return Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).setIssuedAt(new Date())
        .setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret)
        .claim("id", Long.toString(userPrincipal.getId())).claim("email", userPrincipal.getEmail())
        .claim("role", userPrincipal.getRole().getName().toUpperCase())
        .claim("userName", userPrincipal.getUsername())
//        .claim("permissions", getPrivilegeRouteDto(userPrincipal.getRole().getRolePermission()))
        .compact();
  }

  private List<PrivilegeRouteDto> getPrivilegeRouteDto(Set<RolePermission> set) {
    List<PrivilegeRouteDto> permissionRoutes = new ArrayList<PrivilegeRouteDto>();
    mainRouteRepository.findAll().forEach(main -> {
      List<SubRouteDto> subRouteDtoList = new ArrayList<SubRouteDto>();
      PrivilegeRouteDto routeDto = new PrivilegeRouteDto();
      routeDto.setMainRoute(main.getName());
      subRouteRepository.findAll().forEach(sub -> {
        SubRouteDto subRouteDto = new SubRouteDto();
        subRouteDto.setName(sub.getName());
        List<String> permissionList = new ArrayList<String>();
        set.stream().forEach(rp -> {
          if (rp.getPermission().getSubRoute().getId().equals(sub.getId())) {
            permissionList.add(rp.getPermission().getName());
          }
        });
        subRouteDto.setPermissions(permissionList);
        subRouteDtoList.add(subRouteDto);
      });
      routeDto.setSubRoutes(subRouteDtoList);
      permissionRoutes.add(routeDto);
    });
    return permissionRoutes;
  }

  public Long getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return Long.parseLong(claims.getSubject());
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }

}
