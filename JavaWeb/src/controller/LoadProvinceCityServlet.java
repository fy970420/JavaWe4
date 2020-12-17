package controller;

import com.google.gson.Gson;
import model.dao.RegisterLoad;
import model.vo.City;
import model.vo.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/controller/loadprovincecity.do")
public class LoadProvinceCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String provinceCode = request.getParameter("provinceCode");
            String jsonStr = "";
            if(provinceCode==null){
                ArrayList<Province> provinces = RegisterLoad.LoadProvince();
                jsonStr = new Gson().toJson(provinces);
            }
            else{
                ArrayList<City> citys = RegisterLoad.LoadCity(provinceCode);
                jsonStr = new Gson().toJson(citys);
            }
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
