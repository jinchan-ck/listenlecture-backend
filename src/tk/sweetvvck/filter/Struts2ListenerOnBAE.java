package tk.sweetvvck.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
import ognl.OgnlRuntime;
 
public class Struts2ListenerOnBAE implements ServletContextListener,
		HttpSessionListener, HttpSessionAttributeListener {
 
	public void contextInitialized(ServletContextEvent sce) {
		OgnlRuntime.setSecurityManager(null);
	}
 
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
 
	}
 
	 
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
 
	}
 
	 
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
 
	}
 
	 
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
 
	}
 
	 
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
 
	}
 
	 
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
 
	}
 
}