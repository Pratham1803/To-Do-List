package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import model.TaskModel;
import model.DbTask;

public class DashboardServlet extends HttpServlet {

    DbTask dbTask = new DbTask();
    TaskModel taskModel = new TaskModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("index.html");
            return;
        }
        String userId = session.getAttribute("userId").toString();
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>TO-DO List</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>");
        out.println("<form action='Dashboard' method='POST'>");
        out.println("<table border=0 style='margin:0 auto; width:300px;font-size:30'>");
        out.println("<tr>");
        out.println("<td>Task Name: </td>");
        out.println("<td>");
        out.println("<input type='text' placeholder='Enter Task Name Here' name='txtTaskName'/>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Task Description: </td>");
        out.println("<td>");
        out.println("<textarea id='txtDesc' name='txtDesc' rows='5' cols='20'></textarea>");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Due Date: </td>");
        out.println("<td>");
        out.println("<input type='date' id='txtDate' name='txtDate'/>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<center><br/>");
        out.println("<input type='submit' value='Insert To-Do' name='btnSubmit'/><br/>");
        out.println("<a href='LogOutServlet'>Log Out</a>");
        out.println("</form>");
        out.println("</div>");
        out.println("<script>");
        out.println("document.addEventListener('DOMContentLoaded', function () {");
        out.println("var today = new Date().toISOString().split('T')[0];");
        out.println("document.getElementById('txtDate').value = today;");
        out.println("});");
        out.println("</script>");

        out.print("<br/><br/>");
        out.println("<table align='center' width=50% border=1><tr>");
        out.println("<th>Task Name</th><th>Description</th><th>Due Date</th><th>Is Complete</th><th>Update</th><th>Delete</th></tr>");
        for (TaskModel task : dbTask.getAllTasks(userId)) {
            out.println("<tr>");
            out.print("<td>"+task.getTask_name()+"</td>");
            out.print("<td>"+task.getDescription()+"</td>");
            out.print("<td>"+task.getDate()+"</td>");
            
            if(task.getIs_complete() == 0)
                out.print("<td>Completed</td>");
            else
                out.print("<td>InCompleted</td>");
            out.print("<td>"+"<a href='update'>Update</a>"+"</td>");
            out.print("<td>"+"<a href='delete'>Delete</a>"+"</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        taskModel.setTask_id(dbTask.getCount() + 1);
        taskModel.setUser_id((int) session.getAttribute("userId"));
        taskModel.setTask_name(req.getParameter("txtTaskName"));
        taskModel.setDescription(req.getParameter("txtDesc"));
        taskModel.setIs_complete(1); // 0 is true and 1 is true
        Date date = Date.valueOf(req.getParameter("txtDate"));
        taskModel.setDate(date);

        if (dbTask.addTask(taskModel)) {
            resp.sendRedirect("Dashboard");
        } else {
            PrintWriter out = resp.getWriter();
            out.println("Error in adding Task");
        }
    }
}
