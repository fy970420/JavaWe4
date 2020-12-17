package controller;

import model.dao.CreateImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller/createvcode.do")
public class CreateImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //产生随机码
        CreateImage createImage = new CreateImage();
        String code = createImage.CreateCode();
        //保存随机码(用于之后验证)
        HttpSession session = req.getSession();
        session.setAttribute("code",code);
        //画验证码
        BufferedImage image = createImage.CreateImage(code);
        //将图片上传到浏览器
        resp.setContentType("img/jpeg");//设置返回内容
        resp.setHeader("pragma","No-cache");//浏览器不缓存响应内容
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("expires",0);//设置验证码失效时间
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image,"jpeg",out);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
