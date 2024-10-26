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

public class RegisterServlet extends HttpServlet {
       
         @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        
        PrintWriter out = resp.getWriter();
        
        out.println("<html>"
             + "<body bgcolor='lightblue'"
             + "<table style='width:100%; height:100vh; text-align:center;'>"
             + "<tr><td style='text-align:center;  vertical-align:middle;'>"
             + "<div>"
             + "<table border=1 style='margin:0 auto; width:300px;' font-size:19>"
             + "<tr><th colspan=2> REGITRATION FORM "+ "</th></tr>"
             + "<form action='reginsert' method='POST'>"
             + "<tr><td>User id </td><td><input type='text' name='id'/></td></tr>"
             + "<tr><td>Name </td><td><input type='text' name='nm'/></td></tr>"
             + "<tr><td>Email id</td><td><input type='text' name='email'/></td></tr>"
             + "<tr><td>Password</td><td><input type='password' name='pwd'/></td></tr>"
             + "<tr><td colspan=2><center><input type='submit' name='submit'/></center></td></tr>"
             + "</table>"
             + "</div>"
             + "</td>"
             + "</tr>"
             + "</table>"
             + "</body>"
             + "</html>");
    }

}
