package config.security;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class MyBasicAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getOutputStream().println("please log in!");
            resp.getOutputStream().close();
            return;
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
