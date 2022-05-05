package fr.eni.navigation.filters;

import java.io.IOException;

import fr.eni.navigation.model.bll.bo.Role;
import fr.eni.navigation.model.bll.bo.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*/admin/*")
public class Filter2Admin implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		
		User user = (User)request.getSession().getAttribute("user");
		
		if (user != null && Role.ADMIN.equals(user.getRole())) {
			filterChain.doFilter(req, resp);
		} else {
			response.sendRedirect(request.getContextPath() + "/index");
		}

	}

}
