/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Tweet;
import business.User;
import java.io.*;
import java.util.*;
import java.sql.*;

import dataaccess.*;
/**
 *
 * @author meredithbrowne
 */
public class TweetDB {
    public static int insert(Tweet tweet) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO twitterdb.twit (twitID, userID, twitText, twitDate) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, tweet.getTweetID());
            ps.setString(2, tweet.getUserID());
            ps.setString(3, tweet.getText());
            ps.setTimestamp(4, tweet.getTimestamp());
            
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
    
    public static ArrayList<Tweet> selectTweets() throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT twitID, userID, twitText, twitDate, fullName, "
                + "nickname, emailAddress, profilePicture FROM twitterdb.vwtwit";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Tweet> tweets = new ArrayList<>();
            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTweetID(rs.getString("twitID"));
                tweet.setUserID(rs.getString("userID"));
                tweet.setText(rs.getString("twitText"));
                tweet.setTimestamp(rs.getTimestamp("twitDate"));
                tweet.setFullName(rs.getString("fullName"));
                tweet.setNickname(rs.getString("nickname"));
                tweet.setEmailAddress(rs.getString("emailAddress"));
                tweet.setProfilePicture(rs.getString("profilePicture"));
                tweets.add(tweet);
            }
            System.out.println("prepared string: " + ps);
            System.out.println("result set: " + rs.toString());
            return tweets;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
