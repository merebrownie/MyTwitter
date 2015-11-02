/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author meredithbrowne
 */
public class Tweet implements Serializable {
    //defines attributes emailAddress, date, text
    private String emailAddress;
    private String date;
    private String text;
    
    public Tweet() {
        emailAddress = "";
        date = "";
        text = "";
    }
    
    public Tweet(String emailAddress, String date, String text) {
        this.emailAddress = emailAddress;
        this.date = date;
        this.text = text;
    }
    
    //define get/set methods for all attributes
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate() {
        Date today = new Date();
        this.date = today.toString();
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}

