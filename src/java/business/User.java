/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;

/**
 *
 * @javabean for User Entity
 */
public class User implements Serializable {
    //define attributes fullname, ...
    private String emailAddress;
    private String password;
    private String fullName;
    private String birthmonth;
    private String birthdate;
    private String birthyear;
    private String nickname;
    
    //define set/get methods for all attributes.
    public User() {
        fullName = "";
        emailAddress = "";
        birthmonth = "";
        birthdate = "";
        birthyear = "";
        nickname = "";
        password = "";
    }
    
    public User(String emailAddress, String password, String fullName, 
            String birthmonth, String birthdate, String birthyear, String nickname) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.birthmonth = birthmonth;
        this.birthdate = birthdate;
        this.birthyear = birthyear;
        this.nickname = nickname;
        this.password = password;
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
        //birthdate = this.birthmonth.concat(this.birthdate.concat(this.birthyear));
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
}
