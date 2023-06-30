<%@ page import="common.BookmarkGroupMember" %>
<%@ page import="java.util.List" %>
<%@ page import="common.WifiMember" %><%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-28
  Time: 오전 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmark-group</title>
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

<script>
    function deleteBtn(){
        alert('삭제되었습니다');
    }
</script>
<table>
    <thead>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </thead>
    <%
        List<BookmarkGroupMember> bookmarkMember = control.dao.getBookmarkMember();
    %>
    <tbody>
    <%
        for (BookmarkGroupMember member : bookmarkMember) {
            out.write("<tr>");
            out.write("<td>" + member.getGROUP_ID() + "</td>");
            out.write("<td>" + member.getGROUP_NAME() + "</td>");
            out.write("<td>" + member.getGROUP_ORDER() + "</td>");
            out.write("<td>" + member.getREG_DAY() + "</td>");
            out.write("<td>" + member.getCHANGE_DAY() + "</td>");
//            out.write("<td style = \"text-align: center\"><a href = \"../bookmarkEdit?groupId=" + member.getGROUP_ID() + "\">수정</a> " +
//                    "<a href = \"../bookmarkEdit?groupId=" + member.getGROUP_ID() + "\">삭제</a></td>");


            out.write("<td style = \"text-align: center\"><a href = \"bookmark-group-edit.jsp?groupName=" + member.getGROUP_NAME() +
                    "&groupOrder="+member.getGROUP_ORDER()+"&command=edit\">수정</a> " +

                    "<a onclick= \"deleteBtn()\" href = \"../bookmarkGroup?command=delete&groupName="+member.getGROUP_NAME()+"\">삭제</a></td>");
            out.write("</tr>");
        }
    %>

    </tbody>
</table>

</body>
</html>
