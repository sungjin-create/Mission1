package db;

import common.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService {

    //데이터컬럼 생성
    public static void dbCreate(String sql) {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();
            int update = statement.executeUpdate(sql);
            //업데이트 체크
            if (update < 0) {
                System.out.println("create error");
            } else {
                System.out.println("create success");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, statement, connection, null);
        }
    }

    //히스토리 삭제
    public static void dropTable(String tableName) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();

            //DB 테이블 이름 ='WIFIINFO'
            String sql = "drop table " + tableName + ";";
            int update = statement.executeUpdate(sql);

            //업데이트 체크
            if (update < 0) {
                System.out.println("drop table error");
            }
            System.out.println("database create");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, statement, connection, null);
        }
    }

    //JSON에서 받아와서 insert
    public static void dbInsert(int start, int end) {
        String result;
        String address = "http://openapi.seoul.go.kr:8088/584b4b785373756e36337570717a6d/json/TbPublicWifiInfo/" + start + "/" + end;
        StringBuilder sb = new StringBuilder();
        sb.append(address);

        try {
            URL url = new URL(sb.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = br.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject jsonObject_TPWI = (JSONObject) jsonObject.get("TbPublicWifiInfo");
            JSONArray jsonArray = (JSONArray) jsonObject_TPWI.get("row");
            Connection connection = null;
            PreparedStatement pstmt = null;

            String sql = "INSERT INTO WIFIINFO (distance, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2," +
                    " X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR," +
                    " X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try {
                Class.forName(db.classForName);
                connection = DriverManager.getConnection(db.url);
                connection.setAutoCommit(false);
                pstmt = connection.prepareStatement(sql);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    String X_SWIFI_MGR_NO = (String) object.get("X_SWIFI_MGR_NO");
                    String X_SWIFI_WRDOFC = (String) object.get("X_SWIFI_WRDOFC");
                    String X_SWIFI_MAIN_NM = (String) object.get("X_SWIFI_MAIN_NM");
                    String X_SWIFI_ADRES1 = (String) object.get("X_SWIFI_ADRES1");
                    String X_SWIFI_ADRES2 = (String) object.get("X_SWIFI_ADRES2");
                    String X_SWIFI_INSTL_FLOOR = (String) object.get("X_SWIFI_INSTL_FLOOR");
                    String X_SWIFI_INSTL_TY = (String) object.get("X_SWIFI_INSTL_TY");
                    String X_SWIFI_INSTL_MBY = (String) object.get("X_SWIFI_INSTL_MBY");
                    String X_SWIFI_SVC_SE = (String) object.get("X_SWIFI_SVC_SE");
                    String X_SWIFI_CMCWR = (String) object.get("X_SWIFI_CMCWR");
                    int X_SWIFI_CNSTC_YEAR = Integer.parseInt((String) object.get("X_SWIFI_CNSTC_YEAR"));
                    String X_SWIFI_INOUT_DOOR = (String) object.get("X_SWIFI_INOUT_DOOR");
                    String X_SWIFI_REMARS3 = (String) object.get("X_SWIFI_REMARS3");
                    double LAT = Double.parseDouble((String) object.get("LAT"));
                    double LNT = Double.parseDouble((String) object.get("LNT"));
                    String WORK_DTTM = (String) object.get("WORK_DTTM");
                    pstmt.setDouble(1, 0.0);
                    pstmt.setString(2, X_SWIFI_MGR_NO);
                    pstmt.setString(3, X_SWIFI_WRDOFC);
                    pstmt.setString(4, X_SWIFI_MAIN_NM);
                    pstmt.setString(5, X_SWIFI_ADRES1);
                    pstmt.setString(6, X_SWIFI_ADRES2);
                    pstmt.setString(7, X_SWIFI_INSTL_FLOOR);
                    pstmt.setString(8, X_SWIFI_INSTL_TY);
                    pstmt.setString(9, X_SWIFI_INSTL_MBY);
                    pstmt.setString(10, X_SWIFI_SVC_SE);
                    pstmt.setString(11, X_SWIFI_CMCWR);
                    pstmt.setInt(12, X_SWIFI_CNSTC_YEAR);
                    pstmt.setString(13, X_SWIFI_INOUT_DOOR);
                    pstmt.setString(14, X_SWIFI_REMARS3);
                    pstmt.setDouble(15, LAT);
                    pstmt.setDouble(16, LNT);
                    pstmt.setString(17, WORK_DTTM);

                    pstmt.addBatch();
                    pstmt.clearParameters();
                    if ((i % 10000) == 0) {

                        // Batch 실행
                        pstmt.executeBatch();

                        // Batch 초기화
                        pstmt.clearBatch();

                        // 커밋
                        connection.commit();

                    }
                }
                // 커밋되지 못한 나머지 구문에 대하여 커밋
                pstmt.executeBatch();
                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();

                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } finally {
                if (pstmt != null) try {
                    pstmt.close();
                    pstmt = null;
                } catch (SQLException ex) {
                }
                if (connection != null) try {
                    connection.close();
                    connection = null;
                } catch (SQLException ex) {
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    //데이터 입력
    public void dbInsert(String X_SWIFI_MGR_NO,
                         String X_SWIFI_WRDOFC,
                         String X_SWIFI_MAIN_NM,
                         String X_SWIFI_ADRES1,
                         String X_SWIFI_ADRES2,
                         String X_SWIFI_INSTL_FLOOR,
                         String X_SWIFI_INSTL_TY,
                         String X_SWIFI_INSTL_MBY,
                         String X_SWIFI_SVC_SE,
                         String X_SWIFI_CMCWR,
                         int X_SWIFI_CNSTC_YEAR,
                         String X_SWIFI_INOUT_DOOR,
                         String X_SWIFI_REMARS3,
                         double LAT,
                         double LNT,
                         String WORK_DTTM) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        double distance = 0.000;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            String sql = "INSERT INTO WIFIINFO (distance, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2," +
                    " X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR," +
                    " X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, distance);
            preparedStatement.setString(2, X_SWIFI_MGR_NO);
            preparedStatement.setString(3, X_SWIFI_WRDOFC);
            preparedStatement.setString(4, X_SWIFI_MAIN_NM);
            preparedStatement.setString(5, X_SWIFI_ADRES1);
            preparedStatement.setString(6, X_SWIFI_ADRES2);
            preparedStatement.setString(7, X_SWIFI_INSTL_FLOOR);
            preparedStatement.setString(8, X_SWIFI_INSTL_TY);
            preparedStatement.setString(9, X_SWIFI_INSTL_MBY);
            preparedStatement.setString(10, X_SWIFI_SVC_SE);
            preparedStatement.setString(11, X_SWIFI_CMCWR);
            preparedStatement.setInt(12, X_SWIFI_CNSTC_YEAR);
            preparedStatement.setString(13, X_SWIFI_INOUT_DOOR);
            preparedStatement.setString(14, X_SWIFI_REMARS3);
            preparedStatement.setDouble(15, LAT);
            preparedStatement.setDouble(16, LNT);
            preparedStatement.setString(17, WORK_DTTM);

            int affected = preparedStatement.executeUpdate();

            if (affected < 0) {
                System.out.println("저장 실패");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, null, connection, preparedStatement);
        }

    }

    public static void insert(String sql) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();
            int update = statement.executeUpdate(sql);
            if (update < 0) {
                System.out.println("insert error");
            } else {
                System.out.println("insert success");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, statement, connection, null);
        }
    }

    public static WifiMember dbDetail(String detailName) {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);

            //거리에따라 정렬후 조회
            String sql = "select * from WIFIINFO " +
                    "where X_SWIFI_MGR_NO = ? ;";


            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, detailName);

            rs = preparedStatement.executeQuery();

            WifiMember member = new WifiMember();
            //멤버 클래스, 멤버 변수들에 값 저장하기
            while (rs.next()) {
                double distance = 0.000;
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");

                member.setDistance(distance);
                member.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                member.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                member.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                member.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                member.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                member.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                member.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                member.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                member.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                member.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                member.setX_SWIFI_CNSTC_YEAR(Integer.parseInt(X_SWIFI_CNSTC_YEAR));
                member.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                member.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                member.setLAT(Double.parseDouble(LAT));
                member.setLNT(Double.parseDouble(LNT));
                member.setWORK_DTTM(WORK_DTTM);

            }
            System.out.println("조회");
            return member;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(rs, null, connection, null);
        }
        return null;

    }

    //upDate
    public static void dbUpdate(String sql) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();
            int update = statement.executeUpdate(sql);
            if (update < 0) {
                System.out.println("update error");
            } else {
                System.out.println("update success");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, statement, connection, null);
        }
    }

    //데이터베이스 조회
    public static List<WifiMember> dbList() {  //데이터 20개 List에 넣기
        double x = db.latitude;
        double y = db.longitude;
        List<WifiMember> memberList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();

            //거리에따라 정렬후 조회
            String sql = "SELECT X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM, Round((6371 * acos(\n" +
                    "        cos(radians("+x+")) * cos(radians(lnt)) *\n" +
                    "        cos(radians(lat) - radians("+y+")) +\n" +
                    "        sin(radians("+x+")) * sin(radians(lnt))\n" +
                    "    )), 4\n" +
                    "    )\n" +
                    "    as distance\n" +
                    "FROM WIFIINFO\n" +
                    "ORDER BY (\n" +
                    "    (6371 * acos(\n" +
                    "        cos(radians("+x+")) * cos(radians(lnt)) *\n" +
                    "        cos(radians(lat) - radians("+y+")) +\n" +
                    "        sin(radians("+x+")) * sin(radians(lnt))\n" +
                    "    ))\n" +
                    ") ASC\n" +
                    "limit 20;";


            rs = statement.executeQuery(sql);

            //멤버 클래스, 멤버 변수들에 값 저장하기
            while (rs.next()) {
                double distance = rs.getDouble("distance");
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");

                WifiMember member = new WifiMember();
                member.setDistance(distance);
                member.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                member.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                member.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                member.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                member.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                member.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                member.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                member.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                member.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                member.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                member.setX_SWIFI_CNSTC_YEAR(Integer.parseInt(X_SWIFI_CNSTC_YEAR));
                member.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                member.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                member.setLAT(Double.parseDouble(LAT));
                member.setLNT(Double.parseDouble(LNT));
                member.setWORK_DTTM(WORK_DTTM);
                memberList.add(member);
            }
            return memberList;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(rs, statement, connection, null);
        }
        return null;
    }

    //history 조회
    public static List<HistoryMember> dbHistoryList() {  //데이터 20개 List에 넣기
        List<HistoryMember> historyMemberList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();

            //거리에따라 정렬후 조회
            String sql = "select row_number() over (order by NowDate asc) as ID, LAT, LNT, NowDate\n" +
                    "from history\n" +
                    "order by ID desc;";

            rs = statement.executeQuery(sql);

            //멤버 클래스, 멤버 변수들에 값 저장하기
            while (rs.next()) {
                int ID = rs.getInt("ID");
                double LAT = rs.getDouble("LAT");
                double LNT = rs.getDouble("LNT");
                String date = rs.getString("NowDate");

                HistoryMember historyMember = new HistoryMember();
                historyMember.setID(ID);
                historyMember.setLAT(LAT);
                historyMember.setLNT(LNT);
                historyMember.setDate(date);
                historyMemberList.add(historyMember);
            }
            System.out.println("조회");
            return historyMemberList;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(rs, statement, connection, null);
        }
        return null;
    }

    public static List<BookmarkGroupMember> getBookmarkGroupMember(String sql) {  //데이터 20개 List에 넣기
        List<BookmarkGroupMember> bookmarkMemberList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();

            rs = statement.executeQuery(sql);

            //멤버 클래스, 멤버 변수들에 값 저장하기
            while (rs.next()) {
                int GROUP_ID = rs.getInt("GROUP_ID");
                String GROUP_NAME = rs.getString("GROUP_NAME");
                int GROUP_ORDER = rs.getInt("GROUP_ORDER");
                String REG_DAY = rs.getString("REG_DAY");
                String CHANGE_DAY = rs.getString("CHANGE_DAY");

                BookmarkGroupMember bookmarkMember = new BookmarkGroupMember();
                bookmarkMember.GROUP_ID = GROUP_ID;
                bookmarkMember.GROUP_NAME = GROUP_NAME;
                bookmarkMember.GROUP_ORDER = GROUP_ORDER;
                bookmarkMember.REG_DAY = REG_DAY;
                bookmarkMember.CHANGE_DAY = CHANGE_DAY;

                bookmarkMemberList.add(bookmarkMember);
            }
            System.out.println("조회");
            return bookmarkMemberList;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(rs, statement, connection, null);
        }
        return null;
    }

    public static List<BookmarkListMember> getBookmarkListMember(String sql) {  //데이터 20개 List에 넣기
        List<BookmarkListMember> bookmarkListMembers = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();

            rs = statement.executeQuery(sql);

            //멤버 클래스, 멤버 변수들에 값 저장하기
            while (rs.next()) {
                int LIST_ID = rs.getInt("LIST_ID");
                String BM_NAME = rs.getString("BM_NAME");
                String WIFI_NAME = rs.getString("WIFI_NAME");
                String LIST_REG_DAY = rs.getString("LIST_REG_DAY");

                BookmarkListMember bookmarkListMember = new BookmarkListMember();
                bookmarkListMember.LIST_ID = LIST_ID;
                bookmarkListMember.BM_NAME = BM_NAME;
                bookmarkListMember.WIFI_NAME = WIFI_NAME;
                bookmarkListMember.LIST_REG_DAY = LIST_REG_DAY;

                bookmarkListMembers.add(bookmarkListMember);
            }
            System.out.println("조회");
            return bookmarkListMembers;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(rs, statement, connection, null);
        }
        return null;
    }


    public static void bookmarkGroupAdd(String sql) {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();

            int affected = statement.executeUpdate(sql);

            if (affected < 0) {
                System.out.println("저장 실패");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, statement, connection, null);
        }
    }

    //히스토리 저장함수
    public static void dbHistory(double x, double y) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);

            String sql = "insert into history(LAT, LNT, NOWDATE)\n" +
                    "VALUES (?, ?, strftime('%Y-%m-%dT%H:%M:%S', datetime('now', 'localtime')))";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, x);
            preparedStatement.setDouble(2, y);

            int affected = preparedStatement.executeUpdate();

            if (affected < 0) {
                System.out.println("저장 실패");
            }


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, null, connection, preparedStatement);
        }
    }

    public static void dbDelete(String sql) {  //데이터 20개 List에 넣기
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(db.classForName);
            connection = DriverManager.getConnection(db.url);
            statement = connection.createStatement();

            boolean error = statement.execute(sql);
            if (error) {
                System.out.println("delete error");
            } else {
                System.out.println("delete success");
            }

            System.out.println("삭제");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeCheck(null, statement, connection, null);
        }
    }

    //close check
    public static void closeCheck(ResultSet rs, Statement statement, Connection connection, PreparedStatement preparedStatement) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
