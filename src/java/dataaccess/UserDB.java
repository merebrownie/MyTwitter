/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.User;
import java.io.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author mb
 */
public class UserDB {
    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        //System.out.println(pool);
        Connection connection = pool.getConnection();
        //System.out.println(connection);
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO twitterdb.user (userID, fullName, emailAddress, "
                + "birthmonth, birthdate, birthyear, nickname, password,"
                + " profilePicture) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserID());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getEmailAddress());
            ps.setString(4, user.getBirthmonth());
            ps.setString(5, user.getBirthdate());
            ps.setString(6, user.getBirthyear());
            //ps.setString(4, user.getBirthmonth() + user.getBirthdate() + user.getBirthyear());
            ps.setString(7, user.getNickname());
            ps.setString(8, user.getPassword());
            ps.setString(9, user.getProfilePicture());
            System.out.println(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
       
    public static User select(String emailAddress)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM twitterdb.user "
                + "WHERE emailAddress = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getString("userID"));
                user.setFullName(rs.getString("fullName"));
                user.setEmailAddress(rs.getString("emailAddress"));
                user.setBirthmonth(rs.getString("birthmonth"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setBirthyear(rs.getString("birthyear"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setProfilePicture(rs.getString("profilePicture"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<User> selectAll() throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT userID, fullName, emailAddress, birthmonth, "
                + "birthdate, birthyear, nickname, password, profilePicture "
                + "FROM twitterdb.user";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("userID"));
                user.setFullName(rs.getString("fullName"));
                user.setEmailAddress(rs.getString("emailAddress"));
                user.setBirthmonth(rs.getString("birthmonth"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setBirthyear(rs.getString("birthyear"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setProfilePicture(rs.getString("profilePicture"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    /*public static boolean update(User user, String filepath, String newPassword,
            String newFullName, String newBirthmonth, String newBirthdate, 
            String newBirthyear, String newProfilePicture) {*/
    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE twitterdb.user SET "
                + "fullName = ?, "
                + "birthmonth = ?, "
                + "birthdate = ?, "
                + "birthyear = ?, "
                + "password = ?, "
                + "profilePicture = ? "
                + "WHERE emailAddress = ? "
                + "AND userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getBirthmonth());
            ps.setString(3, user.getBirthdate());
            ps.setString(4, user.getBirthyear());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getProfilePicture());
            ps.setString(7, user.getEmailAddress());
            ps.setString(8, user.getUserID());
            System.out.println("prepared string: " + ps);
            //System.out.println("result set: " + rs);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}