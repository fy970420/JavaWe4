package Fliter;

import model.dao.Permission;
import model.dao.UserLogin;
import model.vo.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AutoLoginFilter implements Filter {
    String havePermission;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        User userCookie = new User();
        User userSelect = new User();
        boolean canAutoLogin = false;

        //如果资源不需要被保护  或者  用户刚刚登录完(curUser存在)，直接放行(让后面的权限过滤器再去进行权限判断)
        if(havePermission.indexOf(request.getServletPath())!=-1 || session.getAttribute("curUser")!=null){
            chain.doFilter(req, resp);
        }
        else{//如果用户刚刚没有登录，并且当前资源属于保护资源，那么先尝试借助cookie自动登录，后面在进行权限判断
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){   //从cookie中尝试拿到user
                if("user".equals(cookie.getName())){
                    System.out.println(cookie.getValue());
                    System.out.println(cookie.getValue().split("_")[0]);
                    System.out.println(cookie.getValue().split("_")[1]);
                    userCookie.setUserName(cookie.getValue().split("_")[0]);
                    userCookie.setPassword(cookie.getValue().split("_")[1]);
                    break;
                }
            }

            if(userCookie!=null){     //如果拿到了user
                try {
                    userSelect = UserLogin.CheckLogin(userCookie);
                    if(userSelect.getPassword().equals(userCookie.getPassword())){  //并且账号密码是匹配的
                        canAutoLogin = true;
                        //到此为止，表明cookie中确实存在正确的user，可以自动登录，下面设置当前用户状态和权限状态
                        //设置curUser
                        session.setAttribute("curUser",userSelect.getChrName());
                        //获得可访问资源列表(权限状态)
                        String urlPermissionList = Permission.selectUserPerimission(userSelect);
                        session.setAttribute("urlPermissionList",urlPermissionList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(canAutoLogin){
                chain.doFilter(req, resp);
            }
            else{       //如果不能自动登录，直接跳转到错误页面，显示登录情况有问题
                session.setAttribute("state","登陆情况");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request,resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
        havePermission = config.getInitParameter("havePermission");
    }

}
