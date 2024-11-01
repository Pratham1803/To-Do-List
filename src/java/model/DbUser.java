package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUser {

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

    public boolean addUser(UserModel user) {
        try {
            if (!setConnection()) {
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
        } catch (Exception e) {
            System.out.println("Error in insertion of User: " + e.getMessage());
            return false;
        } finally {
            closeSqlMembers();
            return true;
        }
    }

    public UserModel getUser(String email, String password) {
        UserModel user = null;
        try {
            if (!setConnection()) {
                System.out.println("Failed to connect to the database.");
                return null;
            }

            this.query = "select * from users where email=? and password_hash=?";
            this.pstmt = this.con.prepareStatement(this.query);
            this.pstmt.setString(1, email);
            this.pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserModel();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeSqlMembers();
            return user;
        }
    }

    public int getCount() {
        try {
            if (!setConnection()) {
                System.out.println("Failed to connect to the database.");
                return -1;
            }

            this.query = "select max(user_id) from users";
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