package model.util;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {
    private static String driverFromProperty = null;
    private static String urlFromProperty = null;
    private static String userNameFromProperty = null;
    private static String passwordFromProperty = null;
    private static java.sql.Connection connection = null;

    //静态方法读取properties文件参数并获得连接
    static {
        try {
            //准备读取properties文件
            Properties properties = new Properties();
            InputStream inputStream = new BufferedInputStream(new FileInputStream("E:\\IDEA\\project\\JavaWeb\\src\\model\\resouce\\jdbc.properties"));
            properties.load(new InputStreamReader(inputStream,"utf-8"));
            inputStream.close();
            //从properties中拿到参数
            userNameFromProperty = properties.getProperty("DBUSER");
            passwordFromProperty = properties.getProperty("PASSWORD");
            driverFromProperty = properties.getProperty("DBDRIVER");
            urlFromProperty = properties.getProperty("DBURL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverFromProperty);
        connection = DriverManager.getConnection(urlFromProperty,userNameFromProperty,passwordFromProperty);
        return connection;
    }

    public void closeConnection() throws Exception{
        connection.close();
    }
}
