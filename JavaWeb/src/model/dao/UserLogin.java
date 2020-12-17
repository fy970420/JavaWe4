package model.dao;

import model.util.Connection;
import model.vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserLogin {
    //完成静态方法，产生连接
    static Connection con = new Connection();

    public static User CheckLogin(User user) throws Exception {
        User user_select = new User();
        //根据user去访问数据库，进行结果比对
        java.sql.Connection connection = con.getConnection();
            //完善sql语句(使用PreparedStatement对象的setString方法，对象来自connection对象的PreparedStatement方法)
        String sql = "Select * from t_user where username=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,user.getUserName());
            //访问数据库
        ResultSet resultSet = ps.executeQuery();

            //根据查询结果进行操作
        if(resultSet.next()){
            user_select.setPassword(resultSet.getString("password"));
            user_select.setUserName(resultSet.getString("userName"));
            user_select.setChrName(resultSet.getString("chrName"));
            user_select.setEmail(resultSet.getString("email"));
            user_select.setProvince(resultSet.getString("province"));
            user_select.setProvinceCode(Integer.valueOf(resultSet.getString("provinceCode")));
            user_select.setCity(resultSet.getString("city"));
            user_select.setCityCode(Integer.valueOf(resultSet.getString("cityCode")));
        }

        resultSet.close();
        ps.close();
        con.closeConnection();

        return user_select;
    }

    public static ArrayList<User> QueryAll() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        //根据user去访问数据库，进行结果比对
        java.sql.Connection connection = con.getConnection();
        //完善sql语句(使用PreparedStatement对象的setString方法，对象来自connection对象的PreparedStatement方法)
        String sql = "Select * from t_user";
        PreparedStatement ps = connection.prepareStatement(sql);
        //访问数据库
        ResultSet resultSet = ps.executeQuery();

        //根据查询结果进行操作
        while(resultSet.next()){
            User user_select = new User();
            user_select.setUserName(resultSet.getString("userName"));
            user_select.setPassword(resultSet.getString("password"));
            user_select.setChrName(resultSet.getString("chrName"));
            user_select.setEmail(resultSet.getString("email"));
            users.add(user_select);
        }
        resultSet.close();
        ps.close();
        con.closeConnection();

        return users;
    }
}
