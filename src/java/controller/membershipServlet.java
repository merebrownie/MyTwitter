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
import murach.util.CookieUtil;
import dataaccess.UserDB;
import java.util.ArrayList;
import java.util.Date;
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
        
        //forward to the view
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
        
    }
    
    private String login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String url = "";
        if(user == null) {
            Cookie[] cookies = request.getCookies();
            String emailCookie = CookieUtil.getCookieValue(cookies, "emailAddress");
            //System.out.println(emailAddress);
            String passwordCookie = CookieUtil.getCookieValue(cookies, "password");
            //System.out.println(password);
            
            //if cookie doesn't exist, go to signup page
            if(emailCookie == null || emailCookie.equals("") ||
                    passwordCookie == null || passwordCookie.equals("")) {
                url = "signup.jsp";
            }
            //if cookie exists, create user object and go to homepage
            else {
                String path = getServletContext().getRealPath("/web/database.txt");
                System.out.println(path);
                user = UserDB.select(emailCookie, path);
                System.out.println("Email & path: " + emailCookie + path);
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60*60*24*365);
                url = "/home.jsp";
            }
        }
        //if user object exists, go to homepage
        else {
            url = "/home.jsp";        
        }
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
                
        //save user data to file if the user doesn't already exist
        String path = getServletContext().getRealPath("/database.txt");
        UserDB.insert(user, path);
        String message = "User already exists.";
        //System.out.println(message);
               
        //store the user object as a session attribute
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        //create the Date object & store it in the request
        Date currentDate = new Date();
        request.setAttribute("currentDate", currentDate);
        
        //add a cookie that stores the user's email & password to the browser
        Cookie emailCookie = new Cookie("emailAddress", emailAddress);
        Cookie passwordCookie = new Cookie("password", password);
        emailCookie.setMaxAge(60*60*24*365);
        passwordCookie.setMaxAge(60*60*24*365);
        emailCookie.setPath("/");
        passwordCookie.setPath("/");
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        
        System.out.println(message);
        //redirect to home page
        //return "/home.jsp";
        if(message.equals("")) {
            return "/home.jsp";
        }
        else {
            return "/signup.jsp";
        }
    }

    /*public String validateForm(String emailAddress, String password,
            String fullName, String birthmonth, String birthdate,
            String birthyear, String nickname) {
        //validate parameters
        String message = "";
        if(emailAddress.equals("") || fullName.equals("") || nickname.equals("")) {
            message += "All fields are required. ";
        }
        if(password.length() < 7) {
            message += "Password must be at least 7 characters. ";
        }
        
        if(birthmonth.equals("2")) {
            if(birthdate.equals("29") || birthdate.equals("30")) {
                if(birthdate.equals("29")) {
                    if(birthyear.equals("1980") || birthyear.equals("1984") || 
                    birthyear.equals("1988") || birthyear.equals("1992") || 
                    birthyear.equals("1996") || birthyear.equals("2000") || 
                    birthyear.equals("2004") || birthyear.equals("2008") || 
                    birthyear.equals("2012") || birthyear.equals("2016")) {
                        message += "Invalid birthdate. ";
                } else {
                        message += "Invalid birthdate. ";
                    }
                }
            }
        }
            
        if(birthdate.equals("31")) {
            if(birthmonth.equals("2") || birthmonth.equals("4") || 
                    birthmonth.equals("6") || birthmonth.equals("9") || 
                    birthmonth.equals("11")) {
                message += "Invalid birthdate. ";
            }
        }
        
        if((emailAddress.contains("@") == false) || emailAddress.contains(".") == false) {
            message += "Invalid email address. ";
        }
        return message;
    }*/
    
    
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
