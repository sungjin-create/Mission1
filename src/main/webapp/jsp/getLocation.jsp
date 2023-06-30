<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>getLocation</title>
    <link rel ="stylesheet" type="text/css" href="../css/style.css">
    <style>
        td {
            height: 70px;
            text-align: center;
            font-weight: bolder;
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

<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<script>
    $(function () {
        // Geolocation API에 액세스할 수 있는지를 확인
        if (navigator.geolocation) {
            //위치 정보를 얻기
            navigator.geolocation.getCurrentPosition(function (pos) {
                let latitude;
                latitude = pos.coords.latitude;
                document.getElementById("latitude").value = latitude;
                let longitude;
                longitude = pos.coords.longitude;
                document.getElementById("longitude").value = longitude;
            });
        } else {
            alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
        }
    });
</script>

<form action="../wifiInformation" accept-charset="utf-8" name="" method="get">
    LAT : <input type="text" id = "latitude" name="lat" value="0.0"> ,
    LNT : <input type="text" id = "longitude" name="lnt" value="0.0">
    <input style="position: relative; left:150px" type="submit" value="근처 WIPI 정보 보기">
</form>

<button style="position: relative; right: 140px" onclick=" href='jsp/getLocation.jsp'">내 위치 정보 가져오기</button>

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
        <th>Y좌표</th>
        <th>X좌표</th>
        <th>작업일자</th>
        </thead>
        <tbody>
        <tr>
           <td colspan="17">위치 정보를 입력한 후 조회해주세요.</td>
        </tr>
        </tbody>
</table>
</body>
</html>
