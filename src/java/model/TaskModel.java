/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package model;

import java.sql.Date;

public class TaskModel {
    private String task_id;
    private String task_name;
    private String description;
    private boolean is_complete;
    private Date date;

    public TaskModel() {
    }

    public TaskModel(String task_id, String task_name, String description, boolean is_complete, Date date) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.description = description;
        this.is_complete = is_complete;
        this.date = date;
    }
    
    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_complete() {
        return is_complete;
    }

    public void setIs_complete(boolean is_complete) {
        this.is_complete = is_complete;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
