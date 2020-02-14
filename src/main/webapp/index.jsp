<%@ page language="java" import="java.util.*" pageEncoding="ASCII"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        <title>Tai-Pan Application JSP Page</title>
    	<!--
    	<link rel="stylesheet" type="text/css" href="styles.css">
    	-->
    </head>
    <body bgcolor=white>

        <table border="0">
            <tr>
                <td>
                    <h1>Tai-Pan Application JSP Page</h1>
                    This is the output of a JSP page that is part of the Tai-Pan
                    application.
                </td>
            </tr>
        </table>

        <%= new String("Tai-Pan!") %>

    </body>
</html>
