<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@ page import="java.util.logging.Logger" %>
<%@ page session="false"%>

<% 
LogManager.getLogger("test").error("test Log4J 2");
LoggerFactory.getLogger("test").warn("test Logback");
Logger.getGlobal().warning("test java.util.logging");
%>

Warning logged in Log4J 2 and in Logback and in java.util.logging.
<br />
<a href="../index.jsp">back</a>
