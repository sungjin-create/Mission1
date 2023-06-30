<%@ page import="db.DbService" %>
<%@ page import="common.WifiMember" %>
<%@ page import="common.db" %>
<%@ page import="common.BookmarkGroupMember" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sungj
  Date: 2023-06-27
  Time: ���� 6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<html>
<head>
    <title>Detail-page</title>
    <link rel="stylesheet" type="text/css" href="../css/detail.css">
</head>
<body>
<h1>�������� ���� ���ϱ�</h1>
<br>
<a href="../index.jsp">Ȩ</a> |
<a href="history.jsp">��ġ �����丮</a> |
<a href="../CreateDB">openAPI �������� ���� ��������</a> |
<a href="bookmark-list.jsp">�ϸ�ũ ����</a> |
<a href="bookmark-group.jsp">�ϸ�ũ �׷� ����</a>
<br><br>

<%
    WifiMember wifiMember = control.dao.getDetail();
%>
<form action="../bookmark" accept-charset="UTF-8" method="get">
    <input type="hidden" name = "wifiName" value= <%=wifiMember.getX_SWIFI_MAIN_NM()%>>
    <input type="hidden" name = "command" value = "add">
    <select name="bookmarkName">
        <option value="�ϸ�ũ �׷� �̸� ����"> �ϸ�ũ �׷� �̸� ����</option>
        <%
            List<BookmarkGroupMember> bookmarkMemberList = control.dao.getBookmarkMember();
            for (BookmarkGroupMember bookmarkMember : bookmarkMemberList) {
                out.write("<option value=\"" + bookmarkMember.getGROUP_NAME() + "\">" + bookmarkMember.getGROUP_NAME() + "</option>");
            }
        %>
    </select>
    |
    <input type="submit" value="�ϸ�ũ �߰��ϱ�"/>
</form>
<table>
    <tbody>
    <tr>
        <td>�Ÿ�(Km)</td>
        <td><%=wifiMember.getDistance()%>
        </td>
    </tr>
    <tr>
        <td>������ȣ</td>
        <td><%=wifiMember.getX_SWIFI_MGR_NO()%>
        </td>
    </tr>
    <tr>
        <td>��ġ��</td>
        <td><%=wifiMember.getX_SWIFI_WRDOFC()%>
        </td>
    </tr>
    <tr>
        <td>�������̸�</td>
        <td><%=wifiMember.getX_SWIFI_MAIN_NM()%>
        </td>

    </tr>
    <tr>
        <td>���θ��ּ�</td>
        <td><%=wifiMember.getX_SWIFI_ADRES1()%>
        </td>
    </tr>
    <tr>
        <td>���ּ�</td>
        <td><%=wifiMember.getX_SWIFI_ADRES2()%>
        </td>
    </tr>
    <tr>
        <td>��ġ��ġ(��)</td>
        <td><%=wifiMember.getX_SWIFI_INSTL_FLOOR()%>
        </td>
    </tr>
    <tr>
        <td>��ġ����</td>
        <td><%=wifiMember.getX_SWIFI_INSTL_TY()%>
        </td>
    </tr>
    <tr>
        <td>��ġ���</td>
        <td><%=wifiMember.getX_SWIFI_INSTL_MBY()%>
        </td>
    </tr>
    <tr>
        <td>���񽺱���</td>
        <td><%=wifiMember.getX_SWIFI_SVC_SE()%>
        </td>
    </tr>
    <tr>
        <td>������</td>
        <td><%=wifiMember.getX_SWIFI_CMCWR()%>
        </td>
    </tr>
    <tr>
        <td>��ġ�⵵</td>
        <td><%=wifiMember.getX_SWIFI_CNSTC_YEAR()%>
        </td>
    </tr>
    <tr>
        <td>�ǳ��ܱ���</td>
        <td><%=wifiMember.getX_SWIFI_INOUT_DOOR()%>
        </td>
    </tr>
    <tr>
        <td>WIFI����ȯ��</td>
        <td><%=wifiMember.getX_SWIFI_REMARS3()%>
        </td>
    </tr>
    <tr>
        <td>X��ǥ</td>
        <td><%=wifiMember.getLAT()%>
        </td>
    </tr>
    <tr>
        <td>Y��ǥ</td>
        <td><%=wifiMember.getLNT()%>
        </td>
    </tr>
    <tr>
        <td>�۾�����</td>
        <td><%=wifiMember.getWORK_DTTM()%>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
