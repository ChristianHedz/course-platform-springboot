package com.chris.courseplatform.app.config;

import com.chris.courseplatform.app.exceptions.ResourceNotFoundException;
import com.chris.courseplatform.app.models.Jwt;
import com.chris.courseplatform.app.models.User;
import com.chris.courseplatform.app.repositories.JwtRepository;
import com.chris.courseplatform.app.services.JwtService;
import com.chris.courseplatform.app.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private JwtRepository tokenRepository;
    private UserService userService;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = jwtService.extractTokenFromRequest(request);
        if(jwt == null || jwt.isBlank()){
            filterChain.doFilter(request,response);
            return;
        }
        Optional<Jwt> findToken = tokenRepository.findByToken(jwt);
        boolean isValid = validateToken(findToken);
        if(!isValid){
            findToken.ifPresent(this::updateTokenStatus);
            filterChain.doFilter(request,response);
            return;
        }
        String username = jwtService.extractUsername(jwt);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User","username",username));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username,null, user.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);

    }

    private boolean validateToken(Optional<Jwt> optionalToken) {
        if (optionalToken.isEmpty()){
            return false;
        }
        Jwt token = optionalToken.get();
        Date now = new Date((System.currentTimeMillis()));
        return token.isValid() && token.getExpiration().after(now);
    }

    private void updateTokenStatus(Jwt token) {
        token.setValid(false);
        tokenRepository.save(token);
    }
}
