package com.webapp;

import com.ProductFetcher;
import com.User;
import com.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if(!email.contains("@") || email.isEmpty()){
            request.setAttribute("errMessage", "missing or invalid email");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        if(password.isEmpty()){
            request.setAttribute("errMessage", "Password absent");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPassword(password);

        UserDAO loginDao = new UserDAO();

        User userValidate = null;
        try {
            userValidate = loginDao.authenticateUser(loginBean);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(userValidate != null)
        {
            request.getSession().setAttribute("firstname", userValidate.getFirstname());
            request.getSession().setAttribute("lastname", userValidate.getLastname());
            request.getRequestDispatcher("/shoppingpage.jsp").forward(request, response);
            ProductFetcher pf = new ProductFetcher();
            pf.getProducts();
        }
        else
        {
            request.setAttribute("errMessage", "invalid email or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
