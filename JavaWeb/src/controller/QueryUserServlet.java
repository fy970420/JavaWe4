package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.dao.UserDAO;
import model.vo.Page;
import model.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(urlPatterns = "/controller/queryUser.do")
public class QueryUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收客户端参数
        String queryParams = request.getParameter("queryParams");
        String pageParams = request.getParameter("pageParams");

        //将Json字符串转换为Java对象
        Gson gson = new GsonBuilder().serializeNulls().create();
        HashMap<String,Object> mapPage = gson.fromJson(pageParams,HashMap.class);
        Page page = Page.getPageParams(mapPage);
        User user = new User();
        if(queryParams != null){
            user = gson.fromJson(queryParams,User.class);
        }

        //调用DAO执行处理
        UserDAO dao = new UserDAO();
        ArrayList<User> rows = null;
        try {
            rows = dao.query(user,page);
            for(User u: rows){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int total = 0;
        try {
            total = dao.count(user,page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //转换为Json数据
        HashMap<String,Object> mapReturn = new HashMap<String,Object>();
        mapReturn.put("rows",rows);
        mapReturn.put("total",total);
        String jsonStr = gson.toJson(mapReturn);

        //字符流输出
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
