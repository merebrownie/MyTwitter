/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.*;
import javax.servlet.*;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import business.Tweet;
import business.Tweet;
import dataaccess.TweetDB;
import java.util.UUID;
/**
 *
 * @author meredithbrowne
 */
@WebServlet(name = "tweetServlet", urlPatterns = {"/tweet"})
public class tweetServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get current action
        String action = request.getParameter("action");
        String url = "";
        switch (action) {
            case "null":
                action = "showTweets"; // default action
                break;
            case "showTweets":
                break;
            case "postTweet":
                url = postTweet(request, response);
                break;
            default:
        }
        // create tweets list & store it in the session
        ArrayList<Tweet> tweets = TweetDB.selectTweets();
        HttpSession session = request.getSession();
        session.setAttribute("tweets", tweets);
        //System.out.println("Tweets: " + tweets);
        
        // forward request and response objects to url
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String postTweet(HttpServletRequest request, HttpServletResponse response) {
        // get tweet data from the form
        String userID = request.getParameter("userID");
        String text = request.getParameter("text");
        // store data in a tweet object and save to file
        Tweet tweet = new Tweet();
        //System.out.println("Timestamp: " + tweet.getTimestamp() + "TweetID " + tweet.getTweetID());
        tweet.setUserID(userID);
        tweet.setText(text);
        TweetDB.insert(tweet);
        //set Tweet object in request object and set URL
        request.setAttribute("tweet", tweet);
        return "/home.jsp";
    }
}
