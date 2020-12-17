package controller;

import com.google.gson.Gson;
import model.dao.UserDAO;
import model.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/controller/registerUser.do")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInfoStr = request.getParameter("userInfo");
        String[] userInfo = userInfoStr.split(",");
        User user = new User(userInfo);
        boolean registerResult = false;
        UserDAO dao = new UserDAO();
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try {
            if("insert".equals(userInfo[8])){
                registerResult = dao.insert(user);
            } else {
                registerResult = dao.update(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(registerResult){
            returnMap.put("code",0);
            returnMap.put("info","操作成功");
        } else {
            returnMap.put("code",1);
            returnMap.put("info","操作失败");
        }
        response.setContentType("text/html;charset=utf-8");
        String returnJson = new Gson().toJson(returnMap);
        PrintWriter writer = response.getWriter();
        writer.print(returnJson);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
