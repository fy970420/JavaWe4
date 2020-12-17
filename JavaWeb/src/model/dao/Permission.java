package model.dao;

import model.util.Connection;
import model.vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Permission {
    public static String selectUserPerimission(User user) throws SQLException, ClassNotFoundException {
        String userName = user.getUserName();
        String permissionList = "";
        Connection con = new Connection();
        java.sql.Connection connection = con.getConnection();
        String sql = "select * from t_resource where resourceId in" +
                "(select resourceId from t_role_resource where roleId in" +
                "(select roleId from t_user_role where userName=?))";
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setString(1,userName);
        ResultSet resultSet = pre.executeQuery();
        while(resultSet.next()){
            permissionList += resultSet.getString("url");
        }
        return permissionList;
    }
}

