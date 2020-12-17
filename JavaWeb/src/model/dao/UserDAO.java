package model.dao;

import model.util.Connection;
import model.vo.Page;
import model.vo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    Connection connection = new Connection();

    public User get(String userName) throws Exception{
        User user_select = new User();
        java.sql.Connection con = connection.getConnection();
        String sql = "Select * from t_user where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,userName);
        ResultSet resultSet = ps.executeQuery();
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
        connection.closeConnection();
        return user_select;
    }

    public ArrayList<User> query(User user, Page page) throws Exception{
        ArrayList<User> list = new ArrayList<User>(); // 存放查询结果的集合
        StringBuffer condition = new StringBuffer();// 查询条件
        if (user.getUserName() != null && !"".equals(user.getUserName())) { // 判断是否有该查询条件
            condition.append(" and userName like '%")
                    .append(user.getUserName()).append("%'");
        }
        if (user.getChrName() != null && !"".equals(user.getChrName())) {
            condition.append(" and chrName like '%").append(user.getChrName())
                    .append("%'");
        }
        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            condition.append(" and email like '%").append(user.getEmail())
                    .append("%'");
        }
        if (user.getProvince() != null && !"".equals(user.getProvince())) {
            condition.append(" and B.province like '%")
                    .append(user.getProvince()).append("%'");
        }
        if (user.getCity() != null && !"".equals(user.getCity())) {
            condition.append(" and C.city like '%")
                    .append(user.getCity()).append("%'");
        }
        int begin = page.getPageSize() * (page.getPageNumber() - 1);
        String sql = "select userName,password,chrName,email,A.provinceCode,";
        sql = sql + " B.province,A.cityCode,C.city";
        sql = sql + " from t_user A left join t_province B ";
        sql = sql + " on A.provinceCode = B.provinceCode left join t_city C on A.cityCode = C.cityCode ";
        sql = sql + " where  1=1 ";
        sql = sql + condition + " order by " + page.getSort() + " "
                + page.getSortOrder() + " limit " + begin + ","
                + page.getPageSize();

        // DatabaseConnection类封装了数据库驱动加载和连接
        java.sql.Connection con = connection.getConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                User userResult = new User();
                userResult.setUserName(rs.getString("userName"));
                userResult.setPassword(rs.getString("password"));
                userResult.setChrName(rs.getString("chrName"));
                userResult.setEmail(rs.getString("email"));
                userResult.setProvinceCode(Integer.valueOf(rs.getString("provinceCode")));
                userResult.setProvince(rs.getString("province"));
                userResult.setCityCode(Integer.valueOf(rs.getString("cityCode")));
                userResult.setCity(rs.getString("city"));
                list.add(userResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();// 6.关闭连接
        }
        return list;
    }

    public int count(User user, Page page) throws Exception {
        int selectCount = 0;
        StringBuffer condition = new StringBuffer();// 查询条件
        if (user.getUserName() != null && !"".equals(user.getUserName())) { // 判断是否有该查询条件
            condition.append(" and userName like '%")
                    .append(user.getUserName()).append("%'");
        }
        if (user.getChrName() != null && !"".equals(user.getChrName())) {
            condition.append(" and chrName like '%").append(user.getChrName())
                    .append("%'");
        }
        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            condition.append(" and email like '%").append(user.getEmail())
                    .append("%'");
        }
        if (user.getProvince() != null
                && !"".equals(user.getProvince())) {
            condition.append(" and B.province like '%")
                    .append(user.getProvince()).append("%'");
        }
        if (user.getCity() != null && !"".equals(user.getCity())) {
            condition.append(" and C.city like '%")
                    .append(user.getCity()).append("%'");
        }
        String sql = "select userName,password,chrName,email,A.provinceCode,";
        sql = sql + " B.province,A.cityCode,C.city";
        sql = sql + " from t_user A left join t_province B ";
        sql = sql + " on A.provinceCode = B.provinceCode left join t_city C on A.cityCode = C.cityCode ";
        sql = sql + " where  1=1 ";
        sql = sql + condition + " order by " + page.getSort() + " "
                + page.getSortOrder();

        // DatabaseConnection类封装了数据库驱动加载和连接
        java.sql.Connection con = connection.getConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                selectCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();// 6.关闭连接
        }
        return selectCount;
    }

    public boolean delete(String ids) throws Exception {
        java.sql.Connection con = connection.getConnection();
        String sql = "delete from t_user where userName=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,ids);
        int updateCount = ps.executeUpdate();
        connection.closeConnection();
        if(updateCount==0)
            return false;
        else
            return true;
    }

    public boolean update(User user) throws Exception {
        java.sql.Connection con = connection.getConnection();
        String sql = "update t_user set password=?,chrName=?,email=?,province=?,provinceCode=?,city=?,cityCode=? where userName=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getPassword());
        ps.setString(2,user.getChrName());
        ps.setString(3,user.getEmail());
        ps.setString(4,user.getProvince());
        ps.setInt(5,user.getProvinceCode());
        ps.setString(6,user.getCity());
        ps.setInt(7,user.getCityCode());
        ps.setString(8,user.getUserName());
        int updateCount = ps.executeUpdate();
        connection.closeConnection();
        if(updateCount==0)
            return false;
        else
            return true;
    }

    public boolean insert(User user) throws Exception {
        java.sql.Connection con = connection.getConnection();
        String sql = "Insert into t_user(userName,password,chrName,email,province,provinceCode,city,cityCode) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getUserName());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getChrName());
        ps.setString(4,user.getEmail());
        ps.setString(5,user.getProvince());
        ps.setInt(6,user.getProvinceCode());
        ps.setString(7,user.getCity());
        ps.setInt(8,user.getCityCode());
        int updateCount = ps.executeUpdate();
        connection.closeConnection();
        if(updateCount==0)
            return false;
        else
            return true;
    }
}
