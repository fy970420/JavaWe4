package model.dao;

import model.vo.DownloadList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Download {
    model.util.Connection con = new model.util.Connection();

    public ArrayList<DownloadList> getInfo() throws Exception{
        ArrayList<DownloadList> downloadLists = new ArrayList<>();
        Connection connection = con.getConnection();
        String sql = "Select * from t_downloadlist";
        PreparedStatement ps = connection.prepareStatement(sql);
        //访问数据库
        ResultSet resultSet = ps.executeQuery();

        //根据查询结果进行操作
        while(resultSet.next()){
            DownloadList downloadList = new DownloadList();
            downloadList.setId(Integer.valueOf(resultSet.getString("id")));
            downloadList.setTime(resultSet.getString("time"));
            downloadList.setDescription(resultSet.getString("description"));
            downloadList.setImage(resultSet.getString("image"));
            downloadList.setName(resultSet.getString("name"));
            downloadList.setPath(resultSet.getString("path"));
            downloadList.setSize(Float.valueOf(resultSet.getString("size")));
            downloadList.setStar(Integer.valueOf(resultSet.getString("star")));
            downloadLists.add(downloadList);
        }
        resultSet.close();
        ps.close();
        con.closeConnection();
        return downloadLists;
    }

    public DownloadList Download(int id) throws Exception{
        DownloadList downloadList = new DownloadList();
        Connection connection = con.getConnection();
        String sql = "Select * from t_downloadlist where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,id+"");
        //访问数据库
        ResultSet resultSet = ps.executeQuery();

        //根据查询结果进行操作
        if(resultSet.next()){
            downloadList.setId(Integer.valueOf(resultSet.getString("id")));
            downloadList.setTime(resultSet.getString("time"));
            downloadList.setDescription(resultSet.getString("description"));
            downloadList.setImage(resultSet.getString("image"));
            downloadList.setName(resultSet.getString("name"));
            downloadList.setPath(resultSet.getString("path"));
            downloadList.setSize(Float.valueOf(resultSet.getString("size")));
            downloadList.setStar(Integer.valueOf(resultSet.getString("star")));
            System.out.println(downloadList.getPath());
        }
        resultSet.close();
        ps.close();
        con.closeConnection();
        return downloadList;
    }
}
