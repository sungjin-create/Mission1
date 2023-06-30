package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="deleteDB", value="/deleteDB")
public class HistoryDeleteServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = request.getParameter("date");
        //데이터베이스에서 삭제
        control.dao.dbDelete(date);
        //다시 호출
        response.sendRedirect("jsp/history.jsp");

    }
    public void destroy(){

    }
}
