<%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-28
  Time: 오후 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/bookmarkStyle.css">

</head>
<body>
<h1>북마크 그룹 수정</h1>
<br/>
<a href="../index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리</a> |
<a href="../CreateDB">openAPI 와이파이 정보 가져오기</a> |
<a href="bookmark-list.jsp">북마크 보기</a> |
<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br><br>
<%
    String groupName = request.getParameter("groupName");
    int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
%>
<script>
    function edit(){
        alert('수정');
    }
</script>
<form action="../bookmarkGroup" accept-charset="UTF-8" method="get">
    <input type = "hidden" name = "preGroupName" value = "<%=groupName%>" autocomplete="off">
    <input type = "hidden" name = "preGroupOrder" value = "<%=groupOrder%>" autocomplete="off">
    <input type = "hidden" name = "command" value = "edit">
<table>
    <tr>
        <td>북마크 이름</td>
        <td><input type =text name = "groupName" value="<%=groupName%>"></td>
    </tr>
    <tr>
        <td>순서</td>
        <td><input type =text name = "groupOrder" value="<%=groupOrder%>"></td>
    </tr>
    <tr>
        <td style="background-color: white; border: 1px solid lightgray; color: gray" colspan="2">
            <a href="bookmark-group.jsp">돌아가기</a> | <input type="submit" value = "수정" onclick="edit()">
        </td>
    </tr>
</table>
</form>


</body>
</html>
