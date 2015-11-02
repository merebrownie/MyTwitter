/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import business.User;
import dataaccess.UserDB;
//import java.util.ArrayList;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xl
 */
@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        
        String action = request.getParameter("action");
        //perform action and set url to appropriate page
        //String url = "/home.jsp";
        String url = "";
        if (action.equals("login")) {
            url = login(request, response);
        } else if (action.equals("signup")) {
            url = signup(request, response);
        }
        System.out.println(url);
        
        //create users list and store it in the session
        String path = getServletContext().getRealPath("/WEB-INF/database.txt");
        ArrayList<User> users = UserDB.selectAll(path);
        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        //forward to the view
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
            //System.out.println(request + " " + response);
    }
    
    private String login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        // Get user object if it exists
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if(user != null) {
            System.out.println("Login: " + user.getEmailAddress() + user.getPassword());
        }
        String emailAddress = request.getParameter("emailAddress");
        String password = request.getParameter("password");
        //System.out.println("Login: " + emailAddress + " " + password);
        String url = "/signup.jsp";
        String message = "";
        //System.out.println("login: " + user + " " + user.getEmailAddress() + " " + user.getPassword());
        // If the user does not already exist
        if(user == null) {
            // Get the path of the database.txt file
            String path = getServletContext().getRealPath("/WEB-INF/database.txt");
            // Select the user object
            user = UserDB.select(emailAddress, path);
            System.out.println("Post select: " + user.getEmailAddress());
            session.setAttribute("user", user);
            //session.setMaxInactiveInterval(60*60*24*365);
            session.setMaxInactiveInterval(-1);
            if(checkCredentials(user, emailAddress, password)) {
                url = "/home.jsp";
            } else {
                message = "No user found. Username/Password is incorrect.";
                url = "/login.jsp";
            }
            request.setAttribute("message", message);

            //url = "/home.jsp";
            //check cookies for login info
            /*Cookie[] cookies = request.getCookies();
            String emailCookie = CookieUtil.getCookieValue(cookies, "emailAddress");
            System.out.println("COOKIE: " + emailCookie);
            String passwordCookie = CookieUtil.getCookieValue(cookies, "password");
            System.out.println("COOKIE: " + passwordCookie);
            
            //if cookie doesn't exist, go to signup page
            if(emailCookie == null || emailCookie.equals("") ||
                    passwordCookie == null || passwordCookie.equals("")) {
                url = "/signup.jsp";
                message = "Cookie does not exist.";
                //return url;
            }
            //if cookie exists, create user object and go to homepage
            else {
                // Get the path of the database.txt file
                String path = getServletContext().getRealPath("/database.txt");
                // Select the user object
                user = UserDB.select(emailAddress, path);
                System.out.println("Post select: " + user.getEmailAddress());
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60*60*24*365);
                if(checkCredentials(user, emailAddress, password)) {
                    url = "/home.jsp";
                }
                else {
                    message = "No user found. Username/Password is incorrect.";
                }
                //url = "/home.jsp";
                //request.setAttribute("message", message);
            */
        }
        //if user object exists
        else {
            //System.out.println(user.getEmailAddress() + " " + user.getPassword());
            url = "/home.jsp";        
        }
        //request.setAttribute("message", message);
        return url;
    }
    
    private String signup(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        //get user data
        String emailAddress = request.getParameter("emailAddress");
        String fullName = request.getParameter("fullName");
        String birthmonth = request.getParameter("birthmonth");
        String birthdate = request.getParameter("birthdate");
        String birthyear = request.getParameter("birthyear");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        
        //store the data in a user object
        User user = new User();
        user.setEmailAddress(emailAddress);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBirthmonth(birthmonth);
        user.setBirthdate(birthdate);
        user.setBirthyear(birthyear);
        user.setNickname(nickname);
        
        /*String message = validateForm(emailAddress, password, fullName, 
                birthmonth, birthdate, birthyear, nickname);*/
                
        // save user data to file if the user doesn't already exist
        String path = getServletContext().getRealPath("/WEB-INF/database.txt");
        UserDB.insert(user, path);
        String message = "";
        //message = "User already exists.";
        //System.out.println(message);
               
        // store the user object as a session attribute
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        // create the Date object & store it in the request
        Date currentDate = new Date();
        request.setAttribute("currentDate", currentDate);
        
        // add a cookie that stores the user's email & password to the browser
        Cookie emailCookie = new Cookie("emailAddress", emailAddress);
        Cookie passwordCookie = new Cookie("password", password);
        emailCookie.setMaxAge(60*60*24*365);
        passwordCookie.setMaxAge(60*60*24*365);
        emailCookie.setPath("/");
        passwordCookie.setPath("/");
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        
        //System.out.println(message);
        //return "/home.jsp";
        if(message.equals("")) {
            // redirect to home page
            return "/home.jsp";
        }
        else {
            request.setAttribute("message", message);
            return "/signup.jsp";
        }
    }
    
    public boolean checkCredentials(User user, String emailAddress, String password) {
        if(user.getEmailAddress().equalsIgnoreCase(emailAddress)) {
            if(user.getPassword().equals(password)) {
            System.out.println("Correct credentials. Logging in...");
            return true;
            }
            else {
                System.out.println("Incorrect password.");
                return false;
            }
        } else {
            System.out.println("Incorrect email address.");
            return false;
        }
    }
        
    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
