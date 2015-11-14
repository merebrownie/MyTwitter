/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Tweet;
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
        
        // insert data into file "tweet.txt"
        /*try {
            File file = new File(filepath);
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            
            out.println(tweet.getTweetID() + "|" + tweet.getEmailAddress() + 
                    "|" + tweet.getDate() + "|" + tweet.getText());
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }*/
    }
    
    public static ArrayList<Tweet> selectTweets() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM twitterdb.twit";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTweetID(rs.getString("twitID"));
                tweet.setUserID(rs.getString("userID"));
                tweet.setText(rs.getString("twitText"));
                tweet.setTimestamp(rs.getTimestamp("twitDate"));
                tweets.add(tweet);
            }
            System.out.println("prepared string: " + ps);
            System.out.println("result set: " + rs);
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
        
        //File file = new File(filepath);
        //BufferedReader in = new BufferedReader(new FileReader(file));
            
            
        /*String line = in.readLine();
        while(line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String tweetID = t.nextToken();
                String emailAddress = t.nextToken();
                String date = t.nextToken();
                String text = t.nextToken();
                Tweet tweet = new Tweet(tweetID, emailAddress, date, text);
                tweets.add(tweet);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return tweets;
    }*/
}
