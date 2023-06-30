package test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "test", value = "/test")
public class test extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = request.getParameter("X_SWIFI_MAIN_NM");
        System.out.println(str);
        response.sendRedirect("../jsp/detailPage.jsp");
    }
    public void destroy(){

    }
}