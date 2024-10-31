/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUser{
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private Statement st=null;
    private ResultSet rs=null;
    private String query="";
    
    private boolean setConnection(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String dsn = "jdbc:oracle:thin:@localhost:1521:XE";
            this.con = DriverManager.getConnection(dsn, "SYSTEM", "sys");
            System.out.println("Database Connected");
            return true;
            
        } catch (Exception e) {
            System.out.println("Error in Connection: "+e.getMessage());
            return false;
        }       
    }
    public boolean addUser(UserModel user) {
        try {
            if(!setConnection()){
                return false;
            }
            
            this.query = "INSERT INTO users(user_id, name, email, password_hash) VALUES (?, ?, ?,?)";
            this.pstmt = this.con.prepareStatement(this.query);
            this.pstmt.setInt(1, user.getId());
            this.pstmt.setString(2, user.getName());
            this.pstmt.setString(3, user.getEmail());
            this.pstmt.setString(4, user.getPassword());            
            
            this.pstmt.executeUpdate();            
            System.out.println("Insert Query Fired");
            
            this.con.close();
            this.pstmt.close();
            
            return true;
        } catch (Exception e) {
            System.out.println("Error in insertion of User: "+e.getMessage());
            return false;
        }
    }  
    public UserModel getUser(String email,String password){
        
        try {
        
             if (!setConnection()) {
                System.out.println("Failed to connect to the database.");
                return null;
            }
             
            this.query="select * from users where email=? and password_hash=?";
            this.pstmt= this.con.prepareStatement(this.query);
            this.pstmt.setString(1, email);
            this.pstmt.setString(2, password);
            
        
            rs=pstmt.executeQuery();
            if(rs.next()){
                UserModel user=new UserModel();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbUser.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return null;
        
    }
}
