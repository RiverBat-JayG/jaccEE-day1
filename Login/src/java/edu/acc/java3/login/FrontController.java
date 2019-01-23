package edu.acc.java3.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.length() == 0)
            action = "login";
        String destination;
        switch (action) {
            default:
            case "login": destination = login(request); break;
            case "logout": destination = logout(request); break;
            case "content": destination = content(request); break;
            case "treasure": destination = treasure(request); break;
        }
        String redirect = this.getServletConfig().getInitParameter("redirect.tag");
        if (destination.startsWith(redirect)) {
            response.sendRedirect(destination.substring(
                destination.indexOf(redirect) + redirect.length()));
            return;
        }
        String viewDir = this.getServletConfig().getInitParameter("view.dir");
        String viewType = this.getServletConfig().getInitParameter("view.type");
        request.getRequestDispatcher(viewDir + destination + viewType)
                .forward(request, response);
    }
    
    private String login(HttpServletRequest request) {
        
        if (request.getSession().getAttribute("user") != null) return "content";
        if (request.getMethod().equalsIgnoreCase("GET")) return "login";
        
        String userText = request.getParameter("user");
        String passText = request.getParameter("pass");
        User user = new User(userText, passText);
        
        String destination = "login";
        @SuppressWarnings("unchecked")
        UserDaoImpl dao = (UserDaoImpl)this.getServletContext().getAttribute("dao");
        if (!dao.validate(user))
            request.setAttribute("flash", "Invalid Credentials");
        else if (!dao.authenticate(user))
            request.setAttribute("flash", "Access Denied");
        else {
            request.getSession().setAttribute("user", user);
            destination = "content";
        }
        return destination;
    }
    
    private String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
    
    private String content(HttpServletRequest request) {
        return request.getSession().getAttribute("user") == null ?
                "login" : "content";
    }
    
    private String treasure(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null)
            return "login";
        request.setAttribute("treasure", CandyVan.getTreasure());
        return "treasure";
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
