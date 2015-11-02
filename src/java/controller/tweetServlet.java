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
import dataaccess.TweetDB;
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
        if (action.equals("null")) {
            action = "showTweets"; // default action
        }
        String url ="/home.jsp";
        if (action.equals("showTweets")) {
            
        } else if (action.equals("postTweet")) {
            // get tweet data from the form
            String emailAddress = request.getParameter("emailAddress");
            String text = request.getParameter("text");
            
            // get current date
            Date currentDate = new Date();
            String date = currentDate.toString();
                        
            // store data in a tweet object and save to file
            Tweet tweet = new Tweet(emailAddress, date, text);
            String path = getServletContext().getRealPath("/tweet.txt");
            TweetDB.insert(tweet, path);
            
            //set Tweet object in request object and set URL
            request.setAttribute("tweet", tweet);
            url = "/home.jsp";
            }
        // create tweets list & store it in the session
        String path = getServletContext().getRealPath("/tweet.txt");
        ArrayList<Tweet> tweets = TweetDB.search(path);
        HttpSession session = request.getSession();
        session.setAttribute("tweets", tweets);
        
        // forward request and response objects to url
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    
}
