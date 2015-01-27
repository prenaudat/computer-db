package com.excilys.computerdatabase.dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * AbstractController class for Spring Context initialization in current context.
 * @author excilys
 *
 */
public abstract class AbstractController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
	}
}
