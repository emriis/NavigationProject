package fr.eni.navigation.filters;

import java.io.IOException;

import fr.eni.navigation.model.bll.bo.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/connect/*")
public class Filter1Connect implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp; 
		HttpServletRequest request = (HttpServletRequest) req;
			
		if (request.getSession().getAttribute("user") != null) { 
			filterChain.doFilter(req, resp);
		} else {
			response.sendRedirect(request.getContextPath() + "/index");
		}
		
	}

}
