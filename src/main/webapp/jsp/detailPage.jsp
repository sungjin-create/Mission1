<%@ page import="db.DbService" %>
<%@ page import="common.WifiMember" %>
<%@ page import="common.db" %>
<%@ page import="common.BookmarkGroupMember" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-27
  Time: 오후 6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<html>
<head>
    <title>Detail-page</title>
    <link rel="stylesheet" type="text/css" href="../css/detail.css">
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<br>
<a href="../index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리</a> |
<a href="../CreateDB">openAPI 와이파이 정보 가져오기</a> |
<a href="bookmark-list.jsp">북마크 보기</a> |
<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br><br>

<%
    WifiMember wifiMember = control.dao.getDetail();
%>
<form action="../bookmark" accept-charset="UTF-8" method="get">
    <input type="hidden" name = "wifiName" value= <%=wifiMember.getX_SWIFI_MAIN_NM()%>>
    <input type="hidden" name = "command" value = "add">
    <select name="bookmarkName">
        <option value="북마크 그룹 이름 선택"> 북마크 그룹 이름 선택</option>
        <%
            List<BookmarkGroupMember> bookmarkMemberList = control.dao.getBookmarkMember();
            for (BookmarkGroupMember bookmarkMember : bookmarkMemberList) {
                out.write("<option value=\"" + bookmarkMember.getGROUP_NAME() + "\">" + bookmarkMember.getGROUP_NAME() + "</option>");
            }
        %>
    </select>
    |
    <input type="submit" value="북마크 추가하기"/>
</form>
<table>
    <tbody>
    <tr>
        <td>거리(Km)</td>
        <td><%=wifiMember.getDistance()%>
        </td>
    </tr>
    <tr>
        <td>관리번호</td>
        <td><%=wifiMember.getX_SWIFI_MGR_NO()%>
        </td>
    </tr>
    <tr>
        <td>자치구</td>
        <td><%=wifiMember.getX_SWIFI_WRDOFC()%>
        </td>
    </tr>
    <tr>
        <td>와이파이명</td>
        <td><%=wifiMember.getX_SWIFI_MAIN_NM()%>
        </td>

    </tr>
    <tr>
        <td>도로명주소</td>
        <td><%=wifiMember.getX_SWIFI_ADRES1()%>
        </td>
    </tr>
    <tr>
        <td>상세주소</td>
        <td><%=wifiMember.getX_SWIFI_ADRES2()%>
        </td>
    </tr>
    <tr>
        <td>설치위치(층)</td>
        <td><%=wifiMember.getX_SWIFI_INSTL_FLOOR()%>
        </td>
    </tr>
    <tr>
        <td>설치유형</td>
        <td><%=wifiMember.getX_SWIFI_INSTL_TY()%>
        </td>
    </tr>
    <tr>
        <td>설치기관</td>
        <td><%=wifiMember.getX_SWIFI_INSTL_MBY()%>
        </td>
    </tr>
    <tr>
        <td>서비스구분</td>
        <td><%=wifiMember.getX_SWIFI_SVC_SE()%>
        </td>
    </tr>
    <tr>
        <td>망종류</td>
        <td><%=wifiMember.getX_SWIFI_CMCWR()%>
        </td>
    </tr>
    <tr>
        <td>설치년도</td>
        <td><%=wifiMember.getX_SWIFI_CNSTC_YEAR()%>
        </td>
    </tr>
    <tr>
        <td>실내외구분</td>
        <td><%=wifiMember.getX_SWIFI_INOUT_DOOR()%>
        </td>
    </tr>
    <tr>
        <td>WIFI접속환경</td>
        <td><%=wifiMember.getX_SWIFI_REMARS3()%>
        </td>
    </tr>
    <tr>
        <td>X좌표</td>
        <td><%=wifiMember.getLAT()%>
        </td>
    </tr>
    <tr>
        <td>Y좌표</td>
        <td><%=wifiMember.getLNT()%>
        </td>
    </tr>
    <tr>
        <td>작업일자</td>
        <td><%=wifiMember.getWORK_DTTM()%>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
