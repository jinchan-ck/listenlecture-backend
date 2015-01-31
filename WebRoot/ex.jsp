<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%
	Logger.getAnonymousLogger().log(Level.SEVERE, "test",
			new Exception("test"));
	new Exception("test").printStackTrace(System.out);
%>