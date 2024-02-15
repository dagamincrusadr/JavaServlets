package net.r;

import java.io.IOException;
import java.io.Serial;
import java.security.InvalidParameterException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AccessServlet extends HttpServlet {

    private UserManager userManager;
    private DatabaseManager databaseManager;

    @Serial
    private static final long serialVersionUID = 21l;

    @Override
    public void init() {
        databaseManager = new DatabaseManager();
        userManager = new UserManager(databaseManager.getUsers());
    }

    @Override
    public void destroy() {
        databaseManager.writeUsers(userManager.getUsers());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (request.getParameter("action")) {
            case "login":
                loginAction(request, response);
                break;

            case "register":
                registerAction(request, response);
                break;

            case "logout":
                logOutAction(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No action");
        }

    }

    private void loginAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            if(session.getAttribute("email") == "" | session.getAttribute("email") == null) {
                response.sendRedirect("/catalog/login.htnl");
                session.invalidate();
                return;
            }
            if(session.getAttribute("email") == request.getParameter("email")) {
                response.sendRedirect("/catalog/catalog.htnl");
                return;

            } else {
                response.sendRedirect("/catalog/login.htnl");
                session.invalidate();
                return;
            }
        }

        User userToLogin = new User(request.getParameter("email"), request.getParameter("password"));
        try {
            userManager.loginUser(userToLogin);
        } catch (InvalidParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please enter an email address");
            return;
        }   catch (IllegalStateException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Password");
            return;
        }   catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Email");
            return;
        }
        request.getSession(true).setAttribute("email", userToLogin.getEmail());
        response.sendRedirect("/catalog/catalog.html");

    }

    public void registerAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please try registering again");
        }
        User registeringUser = new User(request.getParameter("email"), request.getParameter("password"), request.getParameter("firstName"), request.getParameter("lastName"));
        try {
            userManager.registerUser(registeringUser);
        } catch (InvalidParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please enter an email");
        } catch (IllegalStateException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is already registered to an account");
        }
        response.sendRedirect("/catalog/login.html");
    }

    public void logOutAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();
        response.sendRedirect("/catalong/login.html");
    }


    
}
