/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.DbUser;
import model.UserModel;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel newUser = new UserModel();
        DbUser dbUser = new DbUser();

        int id = dbUser.getCount();
        if (id == -1) {
            System.out.println("Error: Can not get Id");
            return;
        }
        newUser.setId(dbUser.getCount() + 1);
        newUser.setName(req.getParameter("txtName"));
        newUser.setEmail(req.getParameter("txtEmail"));
        newUser.setPassword(req.getParameter("txtPassword"));
        newUser.setCreated_at(System.currentTimeMillis());
        PrintWriter out = resp.getWriter();
        try {
            if (validateData(newUser, resp)) {
                if (dbUser.addUser(newUser)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("userId", newUser.getId());
                    resp.sendRedirect("Dashboard");
                } else {
                    System.out.println("User Not Registered!!");
                }
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    private boolean validateData(UserModel user, HttpServletResponse resp) {
        boolean flag = false;
        // validation login pending
        return true;
    }
}
