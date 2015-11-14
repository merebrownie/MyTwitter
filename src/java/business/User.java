/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @javabean for User Entity
 */
public class User implements Serializable {
    //define attributes fullname, ...
    private String userID;
    private String emailAddress;
    private String password;
    private String fullName;
    private String birthmonth;
    private String birthdate;
    private String birthyear;
    //private int birthmonth;
    //private int birthdate;
    //private int birthyear;
    //private String birthday;
    private String nickname;
    private String profilePicture;
    
    public User() {
        userID = "";
        fullName = "";
        emailAddress = "";
        nickname = "";
        password = "";
        profilePicture = "";
        //birthday = "";
        birthdate = "";
        birthyear = "";
        birthmonth = "";
    }
    
    public User(String userID, String emailAddress, String password, String fullName, 
            String birthmonth, String birthdate, String birthyear, String nickname,
            String profilePicture) {
    /*public User(String userID, String emailAddress, String password, String fullName,
            String birthday, String nickname, String profilePicture) {*/
        this.userID = userID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.birthmonth = birthmonth;
        this.birthdate = birthdate;
        this.birthyear = birthyear;
        //this.birthday = birthday;
        this.nickname = nickname;
        this.password = password;
        this.profilePicture = profilePicture;
    }
    
    //define set/get methods for all attributes.
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    /*public String getBirthday() {
        return birthday;
    }*/
    
    /*public void setBirthday(String birthday) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(birthyear, birthmonth, birthdate);
        birthday = calendar.toString();
        this.birthday = birthday;
    }*/
    
    /*public Date getBirthdate() {
        return birthdate;
    }*/

    public String getBirthmonth() {
        return birthmonth;
    }
    
    public void setBirthmonth(String birthmonth) {
        this.birthmonth = birthmonth;
    }
    
    public String getBirthdate() {
        return birthdate;
    }
    
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    
    public String getBirthyear() {
        return birthyear;
    }
    
    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getProfilePicture() {
        return profilePicture;
    }
    
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
