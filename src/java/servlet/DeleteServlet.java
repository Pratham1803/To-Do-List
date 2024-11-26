package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.DbTask;

/**
 *
 * @author prath
 */
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            int taskId = Integer.parseInt(id);
            DbTask dbTask = new DbTask();
            boolean res = dbTask.deleteToDo(taskId);
            if(res){
                resp.sendRedirect("Dashboard.jsp");
            } else {
                req.setAttribute("message", "Something went wrong to delete the To Do");
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
