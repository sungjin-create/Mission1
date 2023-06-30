package servlet;


import control.dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookmark", value = "/bookmark")
public class BookmarkServlet extends HttpServlet {
    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        if (command.equals("delete")) {
            int listId = Integer.parseInt(request.getParameter("listId"));
            dao.bookmarkListDelete(listId);
            response.sendRedirect("jsp/bookmark-list.jsp");
        }else{
            String bookmarkName = request.getParameter("bookmarkName");
            String wifiName = request.getParameter("wifiName");
            dao.bookmarkGroupListAdd(bookmarkName, wifiName);
            response.sendRedirect("jsp/bookmark-list.jsp");
        }

    }
    public void destroy(){}
}
