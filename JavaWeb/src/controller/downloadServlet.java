package controller;

import model.dao.Download;
import model.vo.DownloadList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/controller/download.do")
public class downloadServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        String path = "";
        String fileName = "";

        //得到下载对象的详细信息
        Download download = new Download();
        DownloadList downloadList = new DownloadList();
        try {
            downloadList = download.Download(id);
            path = req.getServletContext().getRealPath(downloadList.getPath());
            fileName = path.substring(path.lastIndexOf("\\")+1);
            resp.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
            System.out.println(path);
            InputStream inputStream = new FileInputStream(path);
            ServletOutputStream out = resp.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while((len=inputStream.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            inputStream.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
