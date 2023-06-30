<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-21
  Time: 오후 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>TotalCount</title>
    <style>
        .m {
            text-align: center;
            font-weight: bolder;
            font-size: xx-large;
        }

        .t {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    int total = (int) json.GetJson.getTotal();
%>
<p class="m">총 <%=total%>개의 데이터를 저장하였습니다.</p>
<p class="t"><a href="../index.jsp">홈으로 가기</a></p>

</body>
</html>
