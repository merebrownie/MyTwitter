/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;
import java.sql.*;
import java.util.UUID;
import java.util.*;

/**
 *
 * @author meredithbrowne
 */
public class Tweet implements Serializable {
    //defines attributes tweetID, emailAddress, date, text
    private String tweetID;
    private String userID;
    private String fullName;
    private String nickname;
    private String emailAddress;
    private String profilePicture;
    private Timestamp timestamp;
    private String text;
    
    public Tweet() {
        //tweetID = "";
        userID = "";
        text = "";
        fullName = "";
        nickname = "";
        emailAddress = "";
        profilePicture = "";
        //set date
        Calendar calendar = new GregorianCalendar();
        timestamp = new Timestamp(calendar.getTimeInMillis());
        System.out.println("calendar: " + calendar + " calendar.getTimeInMillis()" + calendar.getTimeInMillis());
        System.out.println("timestamp: " + timestamp);
        
        //generate random tweetID
        tweetID = UUID.randomUUID().toString();
    }

    
    //define get/set methods for all attributes
    public String getTweetID() {
        return tweetID;
    }
    
    public void setTweetID(String tweetID) {
        this.tweetID = tweetID;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        Calendar calendar = new GregorianCalendar();
        timestamp = new Timestamp(calendar.getTimeInMillis());
        this.timestamp = timestamp;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getProfilePicture() {
        return profilePicture;
    }
    
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

