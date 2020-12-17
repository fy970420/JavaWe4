package model.dao;

import model.util.Connection;
import model.vo.City;
import model.vo.Province;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RegisterLoad {
    static Connection con = new Connection();
    public static ArrayList<Province> LoadProvince() throws Exception {
        ArrayList<Province> provinces = new ArrayList<Province>();
        java.sql.Connection connection = con.getConnection();
        String sql = "Select * from t_province";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            Province province = new Province();
            province.setProvince(resultSet.getString("province"));
            province.setProvinceCode(Integer.valueOf(resultSet.getString("provinceCode")));
            provinces.add(province);
        }
        con.closeConnection();
        return provinces;
    }

    public static ArrayList<City> LoadCity(String provinceCode) throws Exception {
        ArrayList<City> citys = new ArrayList<City>();
        java.sql.Connection connection = con.getConnection();
        String sql = "Select * from t_city where provinceCode=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,provinceCode);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            City city = new City();
            city.setProvinceCode(Integer.valueOf(resultSet.getString("provinceCode")));
            city.setCity(resultSet.getString("city"));
            city.setCityCode(Integer.valueOf(resultSet.getString("cityCode")));
            citys.add(city);
        }
        con.closeConnection();
        return citys;
    }

}
