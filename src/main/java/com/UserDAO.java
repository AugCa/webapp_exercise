package com;
import com.webapp.LoginBean;

import java.sql.*;

public class UserDAO {



    public User authenticateUser(LoginBean loginBean) throws SQLException {
        String email = loginBean.getEmail();
        String password = loginBean.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String userNameDB = "";
        String passwordDB = "";

        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE email='" + email + "' AND password='" + password +"'");

            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }catch(SQLException e)
            {
                e.printStackTrace();
            }
            return null;

    }
}