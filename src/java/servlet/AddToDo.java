package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import model.DbTask;
import model.TaskModel;

/**
 *
 * @author prath
 */
public class AddToDo extends HttpServlet {
    
    DbTask dbTask = new DbTask();
    TaskModel taskModel = new TaskModel();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("index.html");
            return;
        }
        
        try {
            taskModel.setTask_id(dbTask.getCount() + 1);
            taskModel.setUser_id((int) session.getAttribute("userId"));
            taskModel.setTask_name(req.getParameter("txtTaskName"));
            taskModel.setDescription(req.getParameter("txtDesc"));
            taskModel.setIs_complete(1); // 0 is true and 1 is true
            Date date = Date.valueOf(req.getParameter("txtDate"));
            taskModel.setDate(date);
            
            if (dbTask.addTask(taskModel)) {
                resp.sendRedirect("Dashboard.jsp");
            } else {
                PrintWriter out = resp.getWriter();
                out.println("Error in adding Task");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Something went Wrong" + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
            rd.forward(req, resp);
        }
    }
}