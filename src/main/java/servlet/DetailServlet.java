package servlet;

import common.db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "detail", value= "/detail")
public class DetailServlet extends HttpServlet {
    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        db.detailName =request.getParameter("number");
        response.sendRedirect("jsp/detailPage.jsp");
    }
    public void destroy(){

    }
}
