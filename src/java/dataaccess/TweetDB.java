/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import business.Tweet;
import java.io.*;
import java.util.*;
/**
 *
 * @author meredithbrowne
 */
public class TweetDB {
    public static boolean insert(Tweet tweet, String filepath) {
        // insert data into file "tweet.txt"
        try {
            File file = new File(filepath);
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            
            out.println(tweet.getEmailAddress() + "|" + tweet.getDate() + "|" +
                    tweet.getText());
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ArrayList<Tweet> search(String filepath)  throws IOException {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        File file = new File(filepath);
        BufferedReader in = new BufferedReader(new FileReader(file));
            
            
        String line = in.readLine();
        while(line != null) {
            try {
                StringTokenizer t = new StringTokenizer(line, "|");
                String emailAddress = t.nextToken();
                String date = t.nextToken();
                String text = t.nextToken();
                Tweet tweet = new Tweet(emailAddress, date, text);
                tweets.add(tweet);
                line = in.readLine();
            } catch (NoSuchElementException e) {
                line = in.readLine();
            }
        }
        in.close();
        return tweets;
    }
}
