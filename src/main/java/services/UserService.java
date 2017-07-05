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
            pstmt = this.dbAccess.conn.prepareStatement("SELECT * FROM API_USERS");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User temp = new User();
                temp.setId(rs.getString("user_id"));
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
            pstmt = this.dbAccess.conn.prepareStatement("SELECT * FROM API_USERS WHERE user_id=? LIMIT 1");
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();

            if(!rs.isBeforeFirst()){        //isBeforeFirst prevents having to backtrack if data is to be read
                return null;
            }
            else {

                while (rs.next()) {
                    selectUser.setId(rs.getString("user_id"));
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

    public User createUser(String id, String name, String email){

        PreparedStatement pstmt = null;
        User newUser = new User();
        newUser.setId(id);
        newUser.setName(name);
        newUser.setEmail(email);


        try {
            pstmt = this.dbAccess.conn.prepareStatement("insert into API_USERS( user_id, user_name,user_email)"+
                                                            "values(?,?,?)");
            pstmt.setString(1,newUser.getId());
            pstmt.setString(2,newUser.getName());
            pstmt.setString(3,newUser.getEmail());
            pstmt.executeUpdate();

            return newUser;

        } catch(SQLException esql){
            System.out.println("Could not create new user");
            return null;
        } catch(Exception e){
            //TODO - Add better exception handling?
            System.out.println(e);
        } finally {

            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            this.dbAccess.closeConnection();
        }
        return newUser;
    }
    // updates an existing user
    //public User updateUser(String id, String name, String email) { .. }

    private void failIfInvalid(String name, String email){}
}
