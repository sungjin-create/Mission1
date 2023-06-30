<%@ page import="db.DbService" %>
<%@ page import="java.util.List" %>
<%@ page import="common.HistoryMember" %><%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-27
  Time: 오전 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>history</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css?ver=1">
    <style>
        th{
            height: 30px;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<br/>

<a href="../index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리</a> |
<a href="../CreateDB">openAPI 와이파이 정보 가져오기</a> |
<a href="bookmark-list.jsp">북마크 보기</a> |
<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br><br>

<table>
    <thead>
    <th>ID</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>조회일자</th>
    <th style="">비고</th>
    </thead>
    <tbody>
    <%
        List<HistoryMember> historyMemberList = control.dao.dbHistoryList();
    %>
    </tbody>
    <%
        for (HistoryMember historyMember : historyMemberList) {
            out.write("<style = \"tr:nth-child(2n) {background-color: lightgray;}\"></style>");
            out.write("<form action=\"../deleteDB\" accept-charset=\"UTF-8\" name=\"\" method=\"get\">");
            out.write("<tr>");
            out.write("<td><input type = \"hidden\" name = \"id\" value = " + historyMember.getID() + ">" + historyMember.getID() + "</td>");
            out.write("<td><input type = \"hidden\" name = \"lat\" value = " + historyMember.getLAT() + ">" + historyMember.getLAT() + "</td>");
            out.write("<td><input type = \"hidden\" name = \"lnt\" value = " + historyMember.getLNT() + ">" + historyMember.getLNT() + "</td>");
            out.write("<td><input type = \"hidden\" name = \"date\" value = '" + historyMember.getDate() + "'>" + historyMember.getDate() + "</td>");
            out.write("<td style=\"text-align: center\"><input type=\"submit\" value=\"삭제\"/></td>");
            out.write("</tr>");
            out.write("</form>");
        }
    %>

</table>
</body>
</html>
