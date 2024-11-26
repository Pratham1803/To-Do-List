package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import model.DbTask;

/**
 *
 * @author prath
 */
public class UpdateTaskStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbTask dbTask = new DbTask();
        String taskId = req.getParameter("taskId");
        String isComplete = req.getParameter("isComplete");

        try {
            boolean success = dbTask.taskComplete(Integer.parseInt(taskId), Integer.parseInt(isComplete));
            if (success) {
                resp.sendRedirect("Dashboard.jsp");
            } else {
                req.setAttribute("message", "Error in Updating Task");
                RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
            rd.forward(req, resp);
        }
    }
}
