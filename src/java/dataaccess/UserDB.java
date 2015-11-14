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
       //implement insert into file "database.txt"
       /*try{
           File file = new File(filepath);
           PrintWriter out = new PrintWriter(new FileWriter(file, true));
           
           //generate 
           
           out.println(user.getEmailAddress() + "|" + user.getUserID() + "|" + 
                   user.getPassword() + "|" + user.getFullName() + "|" + 
                   user.getBirthmonth() + "|" + user.getBirthdate() + "|" + 
                   user.getBirthyear() + "|" + user.getNickname() + "|" + 
                   user.getProfilePicture());
           out.close();
           return true;
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       }*/
    
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
        //System.out.println(emailAddress + filepath);
        //search in the database.txt and find the User, if does not exist
        //return null; if exist make a User object and return it.
        /*try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader in = new BufferedReader(new FileReader(file));
            User user = new User();
        
            String line = in.readLine();
            while(line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                if(t.countTokens() < 7) {
                    return new User("", "", "", "", "", "", "", "", "");
                }
                String token = t.nextToken();
                //System.out.println("Token: " + token);
                if(token.equalsIgnoreCase(emailAddress)) {
                    String userID = t.nextToken();
                    String password = t.nextToken();
                    String fullName = t.nextToken();
                    String birthmonth = t.nextToken();
                    String birthdate = t.nextToken();
                    String birthyear = t.nextToken();
                    String nickname = t.nextToken();
                    String profilePicture = t.nextToken();
                    user.setEmailAddress(emailAddress);
                    user.setUserID(userID);
                    user.setPassword(password);
                    user.setFullName(fullName);
                    user.setBirthmonth(birthmonth);
                    user.setBirthdate(birthdate);
                    user.setBirthyear(birthyear);
                    user.setNickname(nickname);
                    user.setProfilePicture(profilePicture);
                    //System.out.println(user.getEmailAddress());
                    System.out.println(user.getEmailAddress() + " " + 
                            user.getUserID() + " " + user.getPassword()
                            + " " + user.getFullName() + " " + user.getBirthmonth() + " "
                            + " " + user.getBirthdate() + " " + user.getBirthyear() + " "
                            + user.getNickname());
                }
                System.out.println("Line:" + line);
                line = in.readLine();
            }
            in.close();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }*/
        
    }
    
    public static ArrayList<User> selectAll() throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM twitterdb.user";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("userID"));
                user.setFullName(rs.getString("fullname"));
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
        /*BufferedReader in = new BufferedReader(new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String emailAddress = t.nextToken();
                String userID = t.nextToken();
                String password = t.nextToken();
                String fullName = t.nextToken();
                String birthmonth = t.nextToken();
                String birthdate = t.nextToken();
                String birthyear = t.nextToken();
                String nickname = t.nextToken();
                String profilePicture = t.nextToken();
                User user = new User(userID, emailAddress, password, fullName, birthmonth,
                birthdate, birthyear, nickname, profilePicture);
                users.add(user);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return users;
    }*/
    
    /*public static boolean update(User user, String filepath, String newPassword,
            String newFullName, String newBirthmonth, String newBirthdate, 
            String newBirthyear, String newProfilePicture) {*/
    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE twitterdb.user SET "
                + "fullname = ?, "
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
        
        /*File file = new File(filepath);
        FileInputStream fs = null;
        InputStreamReader in = null;
        BufferedReader br = null;
        
        StringBuffer sb = new StringBuffer();
        
        String line;
        
        try {
            fs = new FileInputStream(file);
            in = new InputStreamReader(fs);
            br = new BufferedReader(in);
            
            while (true) {
                line = br.readLine();
                if(line == null) {
                    break;
                }
                sb.append(line);
            }
            System.out.println("StringBuilder: " + sb);
            System.out.println("Line: " + line);
            System.out.println("Email Address: " + user.getEmailAddress());
            int startUser = sb.indexOf(user.getEmailAddress());
            
            
            String currentPassword = user.getPassword();
            if (currentPassword != newPassword) {
                int startPassword = sb.indexOf(currentPassword, startUser);
                int endPassword = sb.indexOf("|", startPassword);
                sb.replace(startPassword, endPassword, newPassword);
                System.out.println(startPassword + " " + endPassword + " " + newPassword);
            }
            
            String currentFullName = user.getFullName();
            if (currentFullName != newFullName) {
                int startFullName = sb.indexOf(currentFullName, startUser);
                int endFullName = sb.indexOf("|", startFullName);
                System.out.println(currentFullName + " " + startFullName + " " + 
                        endFullName + " " + newFullName);
                sb.replace(startFullName, endFullName, newFullName);
            }
            
            String currentBirthmonth = user.getBirthmonth();
            if (currentBirthmonth != newBirthmonth) {
                int startBirthmonth = sb.indexOf(currentBirthmonth, startUser);
                int endBirthmonth = sb.indexOf("|", startBirthmonth);
                System.out.println(startBirthmonth + " " + endBirthmonth + " " + 
                        newBirthmonth + " " + sb.length());
                sb.replace(startBirthmonth, endBirthmonth, newBirthmonth);
            }
            
            String currentBirthdate = user.getBirthdate();
            if (currentBirthdate != newBirthdate) {
                int startBirthdate = sb.indexOf(currentBirthdate, startUser);
                int endBirthdate = sb.indexOf("|", startBirthdate);
                sb.replace(startBirthdate, endBirthdate, newBirthdate);
            }
            
            
            String currentBirthyear = user.getBirthyear();
            if (currentBirthyear != newBirthyear) {
                int startBirthyear = sb.indexOf(currentBirthyear, startUser);
                int endBirthyear = sb.indexOf("|", startBirthyear);
                sb.replace(startBirthyear, endBirthyear, newBirthyear);
            }
            
            String currentProfilePicture = user.getProfilePicture();
            if (currentProfilePicture != newProfilePicture) {
                int startProfilePicture = sb.indexOf(currentProfilePicture, startUser);
                int endProfilePicture = sb.indexOf("|", startProfilePicture);
                sb.replace(startProfilePicture, endProfilePicture, newProfilePicture);
            }

            fs.close();
            in.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            FileWriter filestream = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(filestream);
            out.write(sb.toString());
            out.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}*/
