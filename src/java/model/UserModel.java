/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package model;

import java.sql.*;
import java.util.Date;
public class UserModel{
         private int id;
         private String name;
         private String email;
         private String password;
         private Timestamp created_at;
         
         public UserModel(){}
         
         public UserModel(int id,String name,String email,String password,Timestamp created_at){
                this.id=id;
                this.name=name;
                this.email=email;
                this.created_at=created_at;
         }
         public int getId(){
             return id;
         } 
         public void setId(int id){
             this.id=id;
         }
         public String getName(){
             return name;
         }
         public void setName(String name){
             this.name=name;
         }
         public String getEmail(String parameter){
             return getEmail();
         }

    public String getEmail() {
        return email;
    }
         public void setEmail(String email){
             this.email=email;
         }
         public String getPassword(String parameter){
             return getPassword();
         } 

    public String getPassword() {
        return password;
    }
         public void setPassword(String password){
             this.password=password;
         }
         public Timestamp getCreated_at(){
             return created_at;
         }
         public void setCreated_at(long currentTimeMillis){
             this.created_at=new Timestamp(new Date().getTime());
         }                                                                                                                                                                

}
