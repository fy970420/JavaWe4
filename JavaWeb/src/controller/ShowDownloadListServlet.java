package controller;

import model.dao.Download;
import model.vo.DownloadList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/controller/show.do")
public class ShowDownloadListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Download download = new Download();
            ArrayList<DownloadList> downloadLists = download.getInfo();
            HttpSession session = request.getSession();
            session.setAttribute("downloadLists",downloadLists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
