<%-- 
    Document   : Update.jsp
    Created on : 26-Nov-2024, 8:59:30 PM
    Author     : prath
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.TaskModel, model.DbTask" %>
<%       
    // Use the implicit session object
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect("index.html");
        return;
    }
    String userId = session.getAttribute("userId").toString();
    DbTask dbTask = new DbTask();
    TaskModel task = null;
    try {
        int id = Integer.parseInt(request.getParameter("id"));
        task = dbTask.getTask(id);
    } catch (Exception e) {
        request.setAttribute("message", e.getMessage());
        RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
        rd.forward(request, response);
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update To Do</title>
    </head>
    <body>
        <div>
            <form action="UpdateToDo" method="POST">
                <table border="0" style="margin:0 auto; width:300px;font-size:30">
                    <tr>
                    <input type="hidden" name="id" value=<%=task.getTask_id() %> />
                        <td>Task Name: </td>
                        <td>
                            <input type="text" placeholder="Enter Task Name Here" name="txtTaskName" 
                                   value="<%= task.getTask_name() %>" />
                        </td>
                    </tr>
                    <tr>
                        <td>Task Description: </td>
                        <td>
                            <textarea id="txtDesc" name="txtDesc" rows="5" cols="20"><%= task.getDescription() %></textarea>
                        </td>
                    </tr>                    
                    <tr>
                        <td>Due Date: </td>
                        <td>
                            <input type="date" id="txtDate" name="txtDate" value="<%= task.getDate() %>"/>
                        </td>
                    </tr>
                </table>
                <center><br/>
                    <input type="submit" value="Update To-Do" name="btnSubmit"/><br/>
                    <a href="LogOutServlet">Log Out</a>
            </form>
        </div>
    </body>
</html>
