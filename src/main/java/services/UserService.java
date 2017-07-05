package services;

import model.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by KinshukBasu on 04-Jul-17.
 */
public class UserService {

    private DBAccess dbAccess = new DBAccess();

    // returns a list of all users
    public List<User> getAllUsers(){

        List<User> allUsers = new LinkedList<User>();

        dbAccess.establishConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = this.dbAccess.conn.prepareStatement("SELECT * FROM USERS");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User temp = new User();
                temp.setId(rs.getLong("user_id"));
                temp.setName(rs.getString("user_name"));
                temp.setEmail(rs.getString("user_username"));
                allUsers.add(temp);
            }
        } catch(Exception e){
            //TODO - Add better exception handling?
            System.out.println(e);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException logOrIgnore) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            this.dbAccess.closeConnection();
        }



        return(allUsers);

    }


    // returns a single user by id
    public User getUser(String id){
        return this.getUser(Long.parseLong(id));
    }

    public User getUser(long id) {

        User selectUser = new User();

        dbAccess.establishConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = this.dbAccess.conn.prepareStatement("SELECT * FROM USERS WHERE user_id=? LIMIT 1");
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst()){        //isBeforeFirst prevents having to backtrack if data is to be read
                return null;
            }
            else {

                while (rs.next()) {
                    selectUser.setId(rs.getLong("user_id"));
                    selectUser.setName(rs.getString("user_name"));
                    selectUser.setEmail(rs.getString("user_username"));
                }
            }
        } catch(Exception e){
            //TODO - Add better exception handling?
            System.out.println(e);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException logOrIgnore) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            this.dbAccess.closeConnection();
        }
        return selectUser;
    }
    // creates a new user
    //public User createUser(String name, String email) { .. }
    // updates an existing user
    //public User updateUser(String id, String name, String email) { .. }
}
