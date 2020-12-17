package controller;

import com.google.gson.Gson;
import model.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/controller/deleteUser.do")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        boolean deleteResult = false;
        Map<String,Object> returnMap = new HashMap<String,Object>();
        System.out.println("已经进入deleteUser控制器，ids="+ids);
        System.out.println("ids.contains(\"/\")="+ids.contains("/"));
        try{
            if(!ids.contains("/")){   //不包含/，表示是单一删除
                System.out.println("进行单删");
                UserDAO dao = new UserDAO();
                deleteResult = dao.delete(ids);
            }
            else{
                System.out.println("进行群删");
                String[] idsArr = ids.split("/");
                for(int i=0; i<idsArr.length; i++) {
                    UserDAO dao = new UserDAO();
                    deleteResult = dao.delete(idsArr[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(deleteResult){
            returnMap.put("code",0);
            returnMap.put("info","删除成功");
        } else {
            returnMap.put("code",1);
            returnMap.put("info","删除失败");
        }
        response.setContentType("text/html;charset=utf-8");
        String returnJsonStr = new Gson().toJson(returnMap);
        PrintWriter writer = response.getWriter();
        writer.print(returnJsonStr);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
