<%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-28
  Time: 오후 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/bookmarkStyle.css">

</head>
<body>
<h1>북마크 그룹 추가</h1>
<br/>
<a href="../index.jsp">홈</a> |
<a href="history.jsp"> 위치 히스토리</a> |
<a href="../CreateDB">openAPI 와이파이 정보 가져오기</a> |
<a href="bookmark-list.jsp"> 북마크 보기</a> |
<a href="bookmark-group.jsp"> 북마크 그룹 관리</a>
<br><br>
<script>
    function btn(){
        alert('추가');
    }
</script>

<form action="../bookmarkGroup" accept-charset="UTF-8" method="get">
    <input type = "hidden" name = "command" value = "add">
    <table>
        <tr>
            <td>북마크 이름</td>
            <td><input type =text name = "groupName" autocomplete="off"></td>
        </tr>
        <tr>
            <td>순서</td>
            <td><input type =text name = "order" autocomplete="off"></td>
        </tr>
        <tr>
            <td style="background-color: white; border: 1px solid lightgray" colspan="2">
                <input type="submit" value="추가" onclick="btn();"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
