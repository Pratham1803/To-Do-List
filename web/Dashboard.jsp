<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.TaskModel, model.DbTask" %>
<%
    DbTask dbTask = new DbTask();    
    // Use the implicit session object
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect("index.html");
        return;
    }
    String userId = session.getAttribute("userId").toString();
%>
<!DOCTYPE html>
<html>
    <head>
        <title>TO-DO List</title>
    </head>
    <body>
        <div>
            <form action="DashboardServlet" method="POST">
                <table border="0" style="margin:0 auto; width:300px; font-size:30">
                    <tr>
                  
                    <td>Task Name: </td>
                    <td>
                        <input type="text" placeholder="Enter Task Name Here" name="txtTaskName"/>
                    </td>
                    </tr>
                    <tr>
                        <td>Task Description: </td>
                        <td>
                            <textarea id="txtDesc" name="txtDesc" rows="5" cols="20"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Due Date: </td>
                        <td>
                            <input type="date" id="txtDate" name="txtDate"/>
                        </td>
                    </tr>
                </table>
                <center><br/>
                    <input type="submit" value="Insert To-Do" name="btnSubmit"/><br/>
                    <a href="LogOut">Log Out</a>
            </form>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var today = new Date().toISOString().split('T')[0];
                document.getElementById('txtDate').value = today;
            });
            function handleCheckboxChange(checkbox, taskId) {
                var isComplete = checkbox.checked ? 0 : 1; // Assuming 0 means completed and 1 means not completed
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "UpdateTaskStatusServlet", true); // Adjust the servlet URL as needed
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        console.log(xhr.responseText);
                        // Handle the response if needed
                    }
                };
                xhr.send("taskId=" + taskId + "&isComplete=" + isComplete);
            }

        </script>
        <br/><br/>
        <table align="center" width="50%" border="1">
            <tr>
                <th>Task Name</th>
                <th>Description</th>
                <th>Due Date</th>
                <th>Is Complete</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%
                List<TaskModel> tasks = dbTask.getAllTasks(userId);
                for (TaskModel task : tasks) {
            %>
            <tr>
                <td><%= task.getTask_name() %></td>
                <td><%= task.getDescription() %></td>
                <td><%= task.getDate() %></td>
                <td> <input type="checkbox" name="cbComplete" <%= (task.getIs_complete() == 0) ? "checked" : "" %> onchange="handleCheckboxChange(this, '<%= task.getTask_id() %>')" /> </td>
                <td><a href="Update.jsp?id=<%= task.getTask_id() %>">Update</a></td>
                <td><a href="delete?id=<%= task.getTask_id() %>">Delete</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
