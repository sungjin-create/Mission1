package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookmarkGroup", value = "/bookmarkGroup")
public class BookmarkGroupServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if (command.equals("add")) {//북마크 추가
            String groupName = request.getParameter("groupName");
            int order = Integer.parseInt(request.getParameter("order"));
            control.dao.bookmarkGroupAdd(groupName, order);
            response.sendRedirect("jsp/bookmark-group-add.jsp");

        } else if (command.equals("edit")) {//북마크 수정
            String groupName = request.getParameter("groupName");
            int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
            String preGroupName = request.getParameter("preGroupName");
            int preGroupOrder = Integer.parseInt(request.getParameter("preGroupOrder"));
            control.dao.bookmarkEdit(groupName, preGroupName, groupOrder, preGroupOrder);

            response.sendRedirect("jsp/bookmark-group-edit.jsp?groupName=" + groupName +
                    "&groupOrder=" + groupOrder);

        } else if (command.equals("delete")) {//북마크 삭제
            String groupName = request.getParameter("groupName");
            control.dao.bookmarkDelete(groupName);
            response.sendRedirect("jsp/bookmark-group.jsp");
        }
    }

    public void destroy(){}
}
