package Fliter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class PermissionFilter implements Filter {
    String havePermission;
    String urlPermissionList;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();
        if(havePermission.indexOf(request.getServletPath())!=-1){   //不受保护的资源直接放行
            chain.doFilter(req,resp);
        }
        else{       //受保护的资源，根据urlPermissionList进行判断
            urlPermissionList = (String) session.getAttribute("urlPermissionList");
            if(urlPermissionList.indexOf(request.getServletPath())!=-1){
                chain.doFilter(req,resp);       //如果有权限访问，直接放行
            }
            else{       //如果没有权限访问，跳转到错误页面，显示权限情况有问题
                session.setAttribute("state","权限情况");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
        havePermission = config.getInitParameter("havePermission");
    }

}
