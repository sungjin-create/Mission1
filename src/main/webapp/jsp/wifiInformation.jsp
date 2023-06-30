<%@ page import="db.DbService" %>
<%--<%@ page import="service.serviceName" %>--%>
<%@ page import="java.util.List" %>
<%@ page import="common.WifiMember" %>
<%@ page import="common.db" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wifi-Information</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
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

<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<%
    double longitude = db.longitude;
    double latitude = db.latitude;
%>

<form action="../wifiInformation" accept-charset="utf-8" name="" method="get">
    LAT : <input type="text" id="latitude" name="lat" value="<%=latitude%>"> ,
    LNT : <input type="text" id="longitude" name="lnt" value="<%=longitude%>">
    <input style="position: relative; left:150px" type="submit" value="근처 WIPI 정보 보기">
</form>
<button style="position: relative; right: 140px" onclick=" location.href='getLocation.jsp'">내 위치 정보 가져오기</button>

<br><br>

<table>
    <thead>
    <th>거리(km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치(층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외구분</th>
    <th>WIFI접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
    </thead>
    <%
        List<WifiMember> memberList = control.dao.dbWifiMemberList();
    %>
    <tbody>
    <%
        for (WifiMember member : memberList) {
            out.write("<tr>");
            out.write("<td>" + member.getDistance() + "</td>");
            out.write("<td>" + member.getX_SWIFI_MGR_NO() + "</td>");
            out.write("<td>" + member.getX_SWIFI_WRDOFC() + "</td>");
            out.write("<td><a href = \"../detail?number=" + member.getX_SWIFI_MGR_NO() + "\">"+member.getX_SWIFI_MAIN_NM()+"</a></td>"); //상세페이지 작성
            out.write("<td>" + member.getX_SWIFI_ADRES1() + "</td>");
            out.write("<td>" + member.getX_SWIFI_ADRES2() + "</td>");
            out.write("<td>" + member.getX_SWIFI_INSTL_FLOOR() + "</td>");
            out.write("<td>" + member.getX_SWIFI_INSTL_TY() + "</td>");
            out.write("<td>" + member.getX_SWIFI_INSTL_MBY() + "</td>");
            out.write("<td>" + member.getX_SWIFI_SVC_SE() + "</td>");
            out.write("<td>" + member.getX_SWIFI_CMCWR() + "</td>");
            out.write("<td>" + member.getX_SWIFI_CNSTC_YEAR() + "</td>");
            out.write("<td>" + member.getX_SWIFI_INOUT_DOOR() + "</td>");
            out.write("<td>" + member.getX_SWIFI_REMARS3() + "</td>");
            out.write("<td>" + member.getLNT() + "</td>");
            out.write("<td>" + member.getLAT() + "</td>");
            out.write("<td>" + member.getWORK_DTTM() + "</td>");
            out.write("</tr>");
        }
    %>


    </tbody>

</table>
</body>
</html>
