package com.abi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tokenHeader = request.getHeader("Authorization");
		System.out.println("Token = " + tokenHeader);
		if (tokenHeader != null) {
			String token = tokenHeader.substring(7);
			try {
				if (jwtUtil.isValidToken(token)) {
					String userName = jwtUtil.getUserNameFromToken(token);
					System.out.println("username:" + userName);
					UserDetails user = userDetailsService.loadUserByUsername(userName);
					System.out.println("user " + user.getUsername());
					if (user != null) {
						System.out.println("valid Token : Role= "+user.getAuthorities());
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
								user.getUsername(), user.getPassword(), user.getAuthorities());
						auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpStatus.UNAUTHORIZED.value());
			}
		} 
		filterChain.doFilter(request, response);
	}

}
