<%-- 
    Document   : Error
    Created on : 26-Nov-2024, 8:40:41 PM
    Author     : prath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h3>Error : </h3>
        <p><%= request.getAttribute("message") %></p>
    </body>
</html>
