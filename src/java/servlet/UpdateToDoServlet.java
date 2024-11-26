package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import model.DbTask;
import model.TaskModel;

/**
 *
 * @author prath
 */
public class UpdateToDoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskModel taskModel = new TaskModel();
        DbTask dbTask = new DbTask();

        taskModel.setTask_id(Integer.parseInt(req.getParameter("id")));
        taskModel.setTask_name(req.getParameter("txtTaskName"));
        taskModel.setDescription(req.getParameter("txtDesc"));
        Date date = Date.valueOf(req.getParameter("txtDate"));
        taskModel.setDate(date);

        try {
            boolean res = dbTask.updateTask(taskModel);
            if (res) {
                resp.sendRedirect("Dashboard.jsp");
            } else {
                req.setAttribute("message", "Something went Wrong To Update The Task");
                RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("message", "Something went Wrong" + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
            rd.forward(req, resp);
        }
    }

}
