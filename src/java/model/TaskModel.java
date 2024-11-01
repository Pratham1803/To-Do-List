package model;

import java.sql.Date;

public class TaskModel {
    private int task_id;
    private int user_id;    
    private String task_name;
    private String description;
    private int is_complete;
    private Date date;

    public TaskModel() {
    }

    public TaskModel(int task_id, int user_id, String task_name, String description, int is_complete, Date date) {
        this.task_id = task_id;
        this.user_id = user_id;
        this.task_name = task_name;
        this.description = description;
        this.is_complete = is_complete;
        this.date = date;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(int is_complete) {
        this.is_complete = is_complete;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }       
}
