/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import static javax.lang.model.type.TypeKind.NULL;
import model.DbUser;
import model.UserModel;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PrintWriter out = resp.getWriter();
            
            
            String email=(req.getParameter("txtEmail"));
            String pass=(req.getParameter("txtPassword"));
            
            DbUser dbUser = new DbUser();
            UserModel newUser = dbUser.getUser(email,pass);
            
            if(newUser!=null){
                HttpSession session = req.getSession();
                session.setAttribute("user", newUser);
                resp.sendRedirect("Dashboard.html");
            }
            else{
                 req.setAttribute("Error Message", "INVALID EMAIL OR PASSWORD");
                 req.getRequestDispatcher("Login.html").forward(req, resp);
            }
         
        }
    }
