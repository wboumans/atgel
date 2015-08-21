package org.atgel.web.pipeline;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;

import org.atgel.web.el.I18nELResolver;
import org.atgel.web.el.RepositoryItemELResolver;

  

public class ELContextListener implements ServletContextListener {

	public ELContextListener() {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		JspApplicationContext jspApplicationContext = JspFactory.getDefaultFactory().getJspApplicationContext(servletContext);

		jspApplicationContext.addELResolver(new RepositoryItemELResolver());
		jspApplicationContext.addELResolver(new I18nELResolver()); 
 
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}
}
