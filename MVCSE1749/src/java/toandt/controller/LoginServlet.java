/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toandt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import toandt.login.LoginDAO;
import toandt.util.DBHelper;

/**
 *
 * @author Toan
 */
public class LoginServlet extends HttpServlet {
    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter(); 
        String url=INVALID_PAGE; //send view
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
                //3. call model                
                //3.1 new object
                LoginDAO dao = new LoginDAO();
                //3.2 call object's method
                boolean result = dao.checkLogin(username, password);
                //4. Send view
                if (result==true) {
                    url=SEARCH_PAGE; //send view
                    //send cookies
                    Cookie cookie = new Cookie(username,password);
                    cookie.setMaxAge(60 * 3);
                    response.addCookie(cookie);
                }
        } catch (NamingException ex){
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            //5.Set value Res Obj
            response.sendRedirect(url);
            //RequestDispatcher rd = request.getRequestDispatcher(url);
            //rd.forward(request, response);
            //out.close();
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
