package com.gateway.zuul.security;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class RequestFilter extends ZuulFilter {
    public static final String USER_HEADER = "X-User-Header";

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        
        return authentication != null && authentication.getPrincipal() != null;
    }

    @Override
    public Object run(){
      try {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("user name is -------------zuul  "+userDetails.getUsername());
        requestContext.addZuulRequestHeader(USER_HEADER, userDetails.getUsername());
      } catch (Exception e) {
       System.out.println("exception thrown "+ e);
      }
        return null;
    }
}