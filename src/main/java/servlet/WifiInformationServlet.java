package servlet;

import common.db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "wifiInformation", value = "/wifiInformation")
public class WifiInformationServlet extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double latitude = Double.parseDouble(request.getParameter("lat"));
        double longitude = Double.parseDouble(request.getParameter("lnt"));
        //거리 저장
        control.dao.historyInsert(latitude, longitude); //히스토리 db에 저장
        //히스토리작성
        latitude = 1;
        db.latitude = latitude;
        db.longitude = longitude;

        System.out.println("hello");
        response.sendRedirect("jsp/wifiInformation.jsp");
    }

    public void destroy() {
    }
}
