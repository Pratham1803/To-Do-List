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
import java.sql.*;

public class reginsert extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("welcome");
        String id = req.getParameter("id");
        String nm = req.getParameter("nm");
        String unm = req.getParameter("email");
        String pass = req.getParameter("pwd");

        try {
            //connectivity

            Connection con;
            ResultSet rs;

            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "1234");

            String query = "INSERT INTO users(user_id, name, email, password_hash) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(id));
            pst.setString(2, nm);
            pst.setString(3, unm);
            pst.setString(4, pass);
            pst.executeUpdate();

            pst.close();
            con.close();
            resp.sendRedirect("LoginServlet");
        } catch (Exception ex) {
            ex.printStackTrace();
            out.println("Error: " + ex.getMessage());
        }
        System.out.println("Success");
    }
}
