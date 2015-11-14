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
    //private java.sql.Date date;
    private Timestamp timestamp;
    private String text;
    
    public Tweet() {
        //tweetID = "";
        userID = "";
        text = "";
        
        //set date
        //java.util.Date udate = new java.util.Date();
        //java.sql.Date date = new java.sql.Date(udate.getTime());
        Calendar calendar = new GregorianCalendar();
        timestamp = new Timestamp(calendar.getTimeInMillis());
        //date = new java.sql.Date(calendar.getTimeInMillis());
        //System.out.println("udate: " + udate + " udate.getTime()" + udate.getTime());
        System.out.println("calendar: " + calendar + " calendar.getTimeInMillis()" + calendar.getTimeInMillis());
        //System.out.println("date: " + date);
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
    
    /*public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }*/
    
    /*public java.sql.Date getDate() {
        return date;
    }
    
    public void setDate(java.sql.Date date) {
        //java.util.Date udate = new java.util.Date();
        Calendar calendar = new GregorianCalendar();
        date = new java.sql.Date(calendar.getTimeInMillis());
        this.date = date;
    }*/
    
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
}

