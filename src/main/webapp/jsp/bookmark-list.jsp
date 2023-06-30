<%@ page import="common.BookmarkGroupMember" %>
<%@ page import="java.util.List" %>
<%@ page import="common.BookmarkListMember" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-28
  Time: 오후 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmark-list</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">

</head>
<body>
<h1>북마크 그룹</h1>
<br/>
<a href="../index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리</a> |
<a href="../CreateDB">openAPI 와이파이 정보 가져오기</a> |
<a href="bookmark-list.jsp">북마크 보기</a> |
<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br>

<button type="button" onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>

<table>
    <thead>
    <th>ID</th>
    <th>북마크 이름</th>
    <th>와이파이명</th>
    <th>등록일자</th>
    <th>비고</th>
    </thead>
    <%
        List<BookmarkListMember> bookmarkListMembers = control.dao.getBookmarkListMember();
    %>
    <tbody>
    <%
        for (BookmarkListMember bookmarkListMember : bookmarkListMembers) {
            out.write("<tr>");
            out.write("<td>" + bookmarkListMember.getLIST_ID() + "</td>");
            out.write("<td>" + bookmarkListMember.getBM_NAME() + "</td>");
            out.write("<td>" + bookmarkListMember.getWIFI_NAME() + "</td>");
            out.write("<td>" + bookmarkListMember.getLIST_REG_DAY() + "</td>");

//            out.write("<td><a href = \"../bookmark?command=delete&groupId="+bookmarkListMember.getLIST_ID()+"\">삭제</a></td>");
            out.write("<td style = \"text-align: center\"><button type=\"button\" " +
                    "onclick=\"location.href='../bookmark?command=delete&listId=" +
                    bookmarkListMember.getLIST_ID() + "'\">삭제</button></td>");
            out.write("</tr>");

        }
    %>

    </tbody>
</table>

</body>
</html>
