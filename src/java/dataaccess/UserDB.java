/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.User;
import java.io.*;
import java.util.*;
/**
 *
 * @author mb
 */
public class UserDB {
    public static boolean insert(User user, String filepath) {
       //implement insert into file "database.txt"
       try{
           File file = new File(filepath);
           PrintWriter out = new PrintWriter(new FileWriter(file, true));
           
           out.println(user.getEmailAddress() + "|" + user.getPassword() + "|" +
                   user.getFullName() + "|" + user.getBirthmonth() + "|" +
                   user.getBirthdate() + "|" + user.getBirthyear() + "|" +
                   user.getNickname());
           out.close();
           return true;
       } catch (IOException e) {
           e.printStackTrace();
           return false;
       }
    }
    
    public static User select(String emailAddress, String filepath)
    {
        System.out.println(emailAddress + filepath);
        //search in the database.txt and find the User, if does not exist
        //return null; if exist make a User object and return it.
        try {
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
                    return new User("", "", "", "", "", "", "");
                }
                String token = t.nextToken();
                //System.out.println("Token: " + token);
                if(token.equalsIgnoreCase(emailAddress)) {
                    String password = t.nextToken();
                    String fullName = t.nextToken();
                    String birthmonth = t.nextToken();
                    String birthdate = t.nextToken();
                    String birthyear = t.nextToken();
                    String nickname = t.nextToken();
                    user.setEmailAddress(emailAddress);
                    user.setPassword(password);
                    user.setFullName(fullName);
                    user.setBirthmonth(birthmonth);
                    user.setBirthdate(birthdate);
                    user.setBirthyear(birthyear);
                    user.setNickname(nickname);
                    //System.out.println(user.getEmailAddress());
                    System.out.println(user.getEmailAddress() + " " + user.getPassword()
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
        }
        
    }
    
    public static ArrayList<User> selectAll(String filename) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String line = in.readLine();
        while (line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String emailAddress = t.nextToken();
                String password = t.nextToken();
                String fullName = t.nextToken();
                String birthmonth = t.nextToken();
                String birthdate = t.nextToken();
                String birthyear = t.nextToken();
                String nickname = t.nextToken();
                User user = new User(emailAddress, password, fullName, birthmonth,
                birthdate, birthyear, nickname);
                users.add(user);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return users;
    }
    
    public static boolean update(User user, String filepath, String newPassword,
            String newFullName, String newBirthmonth, String newBirthdate, 
            String newBirthyear) {
        
        File file = new File(filepath);
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
}
