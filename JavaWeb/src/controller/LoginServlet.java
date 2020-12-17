package controller;

import com.google.gson.Gson;
import model.dao.Permission;
import model.dao.UserLogin;
import model.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/controller/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//转码

        String userName = req.getParameter("userName"); //接收数据
        String password = req.getParameter("password");
        String vcode = req.getParameter("vcode");

        UserLogin userLogin = new UserLogin();//Service对象
        User user = new User();//前端输入的参数
        user.setUserName(userName);
        user.setPassword(password);
        Map<String,Object> map = new HashMap<String, Object>();
        //验证码核对
        HttpSession session = req.getSession(); //取出session保存的正确验证码
        String saveCode = (String)session.getAttribute("code");
        if(!vcode.equalsIgnoreCase(saveCode)){
            map.put("code",1);              //错误码1--验证码错误
            map.put("info","验证码错误");
        }
        else{
            //账号密码核对
            try {
                User user_select = userLogin.CheckLogin(user);

                if(user_select.getUserName()==null){
                    map.put("code",2);              //错误码2--用户名错误
                    map.put("info","用户名错误");
                }
                else if(!user_select.getPassword().equals(password)){
                    map.put("code",3);              //错误码3--密码错误
                    map.put("info","密码错误");
                    }
                else{
                    map.put("code",0);              //code为0表示正确
                    session.setAttribute("curUser",user_select.getChrName());
                    //获得可访问资源列表
                    String urlPermissionList = Permission.selectUserPerimission(user_select);
                    session.setAttribute("urlPermissionList",urlPermissionList);

                    //如果设置了免登陆，那么就存储cookie，单选框选中为on，未选中为null
                    if("on".equals(req.getParameter("autoLogin"))){
                        //将用户信息封装成一个Cookie
                        Cookie userCookie =  new Cookie("user",user_select.getUserName()+"_"+user_select.getPassword());
                        userCookie.setMaxAge(7*24*60*60);
                        userCookie.setPath("/");
                        resp.addCookie(userCookie);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String strJson = new Gson().toJson(map);
        resp.setContentType("text/html;charset=utf-8"); //设置响应的内容类型
        PrintWriter out = resp.getWriter();
        out.print(strJson);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

}
