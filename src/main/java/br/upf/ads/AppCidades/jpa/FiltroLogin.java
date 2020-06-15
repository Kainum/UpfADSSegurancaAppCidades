package br.upf.ads.AppCidades.jpa;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.upf.ads.AppCidades.controller.LoginControle;

@WebFilter(urlPatterns={"/faces/App/*"})
public class FiltroLogin implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = ((HttpServletRequest) arg0);
		HttpServletResponse res = (HttpServletResponse) arg1;
		HttpSession ses = req.getSession();
		// --------------------------------------
		LoginControle lc = (LoginControle) ses.getAttribute("loginControle");
		//if ((lc == null) || (lc.getUsuarioLogado() == null))
			// está tentando um acesso indevido
		//	res.sendRedirect("/AppCidades/faces/Login/LoginForm.xhtml");
		//else
			// passou pela autenticação e logou corretamente! Pode seguir!!!
			arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
