/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.DbUser;
import model.UserModel;

public class RegisterServlet extends HttpServlet {
       
         @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel newUser = new UserModel();
        newUser.setId(Integer.parseInt(req.getParameter("txtId")));
        newUser.setName(req.getParameter("txtName"));
        newUser.setEmail(req.getParameter("txtEmail"));
        newUser.setPassword(req.getParameter("txtPassword"));
        newUser.setCreated_at(System.currentTimeMillis());
       PrintWriter out = resp.getWriter();
        try{
        if (validateData(newUser, resp)) {
            DbUser dbUser = new DbUser();
            
            
            if (dbUser.addUser(newUser)) {                
                out.print("<h6>user Registered!!</h6>");
            }else{
                out.print("<h6>Error in Insertion!!</h6>");
            }
        }
        }catch(Exception e){
            out.println(e.getMessage());
        }
    }
    private boolean validateData(UserModel user, HttpServletResponse resp) {
        boolean flag = false;
        // validation login pending
        return true;
    }
}
