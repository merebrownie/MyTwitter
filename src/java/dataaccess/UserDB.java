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
 * @author xl
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
}
