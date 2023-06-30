package servlet;

import control.dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateDB", value = "/CreateDB")
public class CreateDBServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        dao.dropTable("WIFIINFO");
        dao.dropTable("HISTORY");
        dao.dropTable("BOOKMARKLIST");
        dao.dropTable("BOOKMARKGROUP");
        dao.dbBookmarkCreate();
        dao.dbBookmarkListCreate();
        dao.dbWifiInfoCreate();
        dao.dbHistoryCreate();
        dao.dbWifiDataInsert();

        response.sendRedirect("jsp/totalCount.jsp");

    }
    public void destroy(){

    }

}
