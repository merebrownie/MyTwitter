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
import java.util.*;

import business.User;
import dataaccess.UserDB;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author mb
 */
@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, //2mb
                maxFileSize=1024*1024*10,       //10mb
                maxRequestSize=1024*1024*50)    //50mb
public class membershipServlet extends HttpServlet {
    // directory to save profile picture files
    private static final String SAVE_DIR = "profilePictures";
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
        // perform action and set url to appropriate page
        String url = "";
        switch (action) {
            case "login":
                url = login(request, response);
                break;
            case "signup":
                url = signup(request, response);
                break;
            case "update":
                url = update(request, response);
                break;
            case "logout":
                url = logout(request, response);
            default:
        }
        
        // create users list and store it in the session
        ArrayList<User> users = UserDB.selectAll();
        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        // forward to the view
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }
    
    private String login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        // Get user object if it exists
        User user = (User) session.getAttribute("user");
        if(user != null) {
            System.out.println("Login: " + user.getEmailAddress() + user.getPassword());
        }
        String emailAddress = request.getParameter("emailAddress");
        String password = request.getParameter("password");
        String url = "/signup.jsp";
        String message = "";
        // If the user does not already exist
        if(user == null) {
            // Select the user object
            user = UserDB.select(emailAddress);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(-1);
            //if(checkCredentials(user, emailAddress, password)) {
                url = "/home.jsp";
            //} else {
                //message = "No user found. Username/Password is incorrect.";
                //url = "/login.jsp";
            //}
            request.setAttribute("message", message);
        }
        else {
            url = "/home.jsp";        
        }
        return url;
    }
    
    private String signup(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        //get user data
        String emailAddress = request.getParameter("emailAddress");
        String fullName = request.getParameter("fullName");
        String birthmonth = request.getParameter("birthmonth");
        String birthdate = request.getParameter("birthdate");
        String birthyear = request.getParameter("birthyear");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String profilePicture = request.getParameter("profilePicture");
        System.out.println("filename: " + profilePicture);
        
        profilePicture = addProfilePicture(request, profilePicture);
        
        //generate random userID
        UUID userID = UUID.randomUUID();
        
        //store the data in a user object
        User user = new User();
        user.setEmailAddress(emailAddress);
        user.setUserID(userID.toString());
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBirthmonth(birthmonth);
        user.setBirthdate(birthdate);
        user.setBirthyear(birthyear);
        user.setNickname(nickname);
        user.setProfilePicture(profilePicture);
        
        // save user data to file if the user doesn't already exist
        UserDB.insert(user);
        String message = "";
               
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
    
    private String update(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException{
        HttpSession session = request.getSession();
        // Get user object if it exists
        User user = (User) session.getAttribute("user");
        String newPassword = request.getParameter("password");
        String newFullName = request.getParameter("fullName");
        String newBirthmonth = request.getParameter("birthmonth");
        String newBirthdate = request.getParameter("birthdate");
        String newBirthyear = request.getParameter("birthyear");
        String newProfilePicture = request.getParameter("profilePicture");
        System.out.println("Profile Picture: " + newProfilePicture);
        
        newProfilePicture = addProfilePicture(request, newProfilePicture);
        
        user.setPassword(newPassword);
        user.setFullName(newFullName);
        user.setBirthmonth(newBirthmonth);
        user.setBirthdate(newBirthdate);
        user.setBirthyear(newBirthyear);
        user.setProfilePicture(newProfilePicture);
        UserDB.update(user);
        
        // store the user object as a session attribute
        session.setAttribute("user", user);
        ArrayList<User> users = UserDB.selectAll();
        request.setAttribute("users", users);
        
        // add a cookie that stores the user's email & password to the browser
        Cookie emailCookie = new Cookie("emailAddress", user.getEmailAddress());
        Cookie passwordCookie = new Cookie("password", newPassword);
        emailCookie.setMaxAge(60*60*24*365);
        passwordCookie.setMaxAge(60*60*24*365);
        emailCookie.setPath("/");
        passwordCookie.setPath("/");
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        
        return "/home.jsp";
    }
    
    /*public boolean checkCredentials(User user, String emailAddress, String password) {
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
    }*/
        
    private String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            System.out.println("Logging out...");
        }
        //request.logout();
        
        return "/login.jsp";
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

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    private String addProfilePicture(HttpServletRequest request, String newProfilePicture) throws IOException, ServletException {
        // get absolute path of the web app
        String appPath = request.getServletContext().getRealPath("");
        // construct directory to save the file
        String savePath = appPath + SAVE_DIR;
        System.out.println("App path: " + appPath);
        System.out.println("Save path: " + savePath);
                
        // create save directory if it doesn't already exist
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        //get the file name & write the file
        for (Part part : request.getParts()) {
            // get timestamp to ensure unique filenames
            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
            String timestamp = formatter.format(today);
            System.out.println("Image timestamp: " + timestamp);
            
            String fileName = timestamp + extractFileName(part);
            
            //request.setAttribute("profilePicture", fileName);
            if (extractFileName(part) != "") {
                part.write(savePath + File.separator + fileName);
                newProfilePicture = fileName;
            }
            System.out.println("newProfilePicture" + newProfilePicture);
            System.out.println("Filename: " + fileName);
        }
        request.setAttribute("message", "Successful upload.");
        return newProfilePicture;
    }

    
}
