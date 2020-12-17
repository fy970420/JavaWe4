package controller;

import com.google.gson.Gson;
import model.dao.UserLogin;
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
import java.util.Map;

@WebServlet(urlPatterns = "/controller/checkUserAndEmail.do")
public class CheckUserNameAndEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        ArrayList<User> users = null;
        try {
            users = UserLogin.QueryAll();
            Map<String,Object> map = new HashMap<String,Object>();
            for(int i=0; i<users.size(); i++) {
                if (users.get(i).getUserName().equals(userName)) {
                    map.put("code", 1);
                    map.put("info", "该用户名已被注册");
                } else if (users.get(i).getEmail().equals(email)) {
                    map.put("code", 2);
                    map.put("info", "该邮箱已被注册");
                }
            }

            String jsonStr = new Gson().toJson(map);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(jsonStr);
            printWriter.flush();
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
