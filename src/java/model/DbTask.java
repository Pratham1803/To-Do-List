package model;

import java.sql.*;
import java.util.ArrayList;

public class DbTask {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String query = "";

    private boolean setConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String dsn = "jdbc:oracle:thin:@localhost:1521:XE";
            this.con = DriverManager.getConnection(dsn, "SYSTEM", "1234");
            System.out.println("Database Connected");
            return true;

        } catch (Exception e) {
            System.out.println("Error in Connection: " + e.getMessage());
            return false;
        }
    }

    public boolean addTask(TaskModel task) {
        try {
            if (!setConnection()) {
                return false;
            }

            this.query = "Insert into tasks(task_id,user_id,task_name,task_desc,is_complete,task_date) values(?,?,?,?,?,?)";
            this.pstmt = this.con.prepareStatement(this.query);
            this.pstmt.setInt(1, task.getTask_id());
            this.pstmt.setInt(2, task.getUser_id());
            this.pstmt.setString(3, task.getTask_name());
            this.pstmt.setString(4, task.getDescription());
            this.pstmt.setInt(5, task.getIs_complete());
            this.pstmt.setDate(6, task.getDate());

            this.pstmt.executeUpdate();
            System.out.println("DbTask:: Task Added.");
            return true;
        } catch (Exception e) {
            System.out.println("Error is Inserting Task:: " + e.getMessage());
            return false;
        } finally {
            closeSqlMembers();
        }
    }

    public boolean updateTask(TaskModel task) throws Exception {
        try {
            if (!setConnection()) {
                return false;
            }
            this.query = "update tasks set task_name = ?, task_desc = ?, task_date = ? where task_id = ?";
            this.pstmt = this.con.prepareStatement(this.query);
            this.pstmt.setString(1, task.getTask_name());
            this.pstmt.setString(2, task.getDescription());
            this.pstmt.setDate(3, task.getDate());
            this.pstmt.setInt(4, task.getTask_id());

            int res = this.pstmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int getCount() {
        try {
            if (!setConnection()) {
                return -1;
            }

            this.query = "select max(task_id) from tasks";
            this.st = this.con.createStatement();

            this.rs = this.st.executeQuery(this.query);
            if (this.rs.next()) {
                int count = this.rs.getInt(1);
                return count;
            } else {
                System.out.println("No Data Found in Id of user");
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println("Error: Exception in returnign max: " + ex.getMessage());
            return -1;
        } finally {
            closeSqlMembers();
        }
    }

    public ArrayList<TaskModel> getAllTasks(String userId) {
        ArrayList<TaskModel> arrTasks = new ArrayList<TaskModel>();

        try {
            setConnection();
            this.query = "select * from tasks where user_id=" + userId;
            this.st = this.con.createStatement();
            this.rs = this.st.executeQuery(this.query);

            while (this.rs.next()) {
                TaskModel task = new TaskModel();
                task.setTask_id(this.rs.getInt(1));
                task.setUser_id(this.rs.getInt(2));
                task.setTask_name(this.rs.getString(3));
                task.setDescription(this.rs.getString(4));
                task.setIs_complete(this.rs.getInt(5));
                Date date = this.rs.getDate(6);
                task.setDate(date);
                arrTasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error to fetch all Tasks:: " + e.getMessage());
            return null;
        } finally {
            closeSqlMembers();
        }

        return arrTasks;
    }

    public TaskModel getTask(int id) throws Exception {
        try {
            if (!setConnection()) {
                return null;
            }
            TaskModel taskModel = new TaskModel();

            this.query = "Select * from tasks where task_id = ?";
            this.pstmt = this.con.prepareStatement(this.query);
            this.pstmt.setInt(1, id);
            this.rs = this.pstmt.executeQuery();
            this.rs.next();
            taskModel.setTask_id(id);
            taskModel.setTask_name(rs.getString("task_name"));
            taskModel.setDescription(rs.getString("task_desc"));
            taskModel.setUser_id(rs.getInt("user_id"));
            taskModel.setIs_complete(rs.getInt("is_complete"));
            taskModel.setDate(rs.getDate("task_date"));

            return taskModel;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            closeSqlMembers();
        }
    }

    public boolean taskComplete(int taskId, int isComplete) throws Exception {
        try {
            if (!setConnection()) {
                return false;
            }
            this.query = "update tasks set is_complete=? where task_id=?";
            this.pstmt = this.con.prepareStatement(this.query);
            this.pstmt.setInt(1, isComplete);
            this.pstmt.setInt(2, taskId);
            int res = this.pstmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            closeSqlMembers();
        }
    }

    public boolean deleteToDo(int id) throws Exception {
        try {
            if (!setConnection()) {
                return false;
            }
            this.query = "DELETE from tasks where task_id = ?";
            this.pstmt = this.con.prepareStatement(this.query);
            this.pstmt.setInt(1, id);
            int res = this.pstmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            closeSqlMembers();
        }
    }

    private void closeSqlMembers() {
        try {
            if (this.con != null) {
                this.con.close();
            }
            if (this.pstmt != null) {
                this.pstmt.close();
            }
            if (this.st != null) {
                this.st.close();
            }
            if (this.rs != null) {
                this.rs.close();
            }
        } catch (Exception e) {
            System.out.println("Error is Closing in DbUser:: " + e.getMessage());
        }
    }
}