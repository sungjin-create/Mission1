package control;

import common.*;
import db.DbService;
import json.GetJson;

import java.io.IOException;
import java.util.List;

public class dao {
//------------북마크 관련---------------------------------------

    public static void dbBookmarkCreate() {
        String sql = "CREATE TABLE `BOOKMARKGROUP` (\n" +
                "    'GROUP_ID'   INTEGER PRIMARY KEY autoincrement,\n" +
                "\t`GROUP_NAME` VARCHAR(50) , -- 북마크그룹이름\n" +
                "\t`GROUP_ORDER` INTEGER     NULL    , -- 순서\n" +
                "\t`REG_DAY`    VARCHAR(30) NULL     , -- 등록일자\n" +
                "\t`CHANGE_DAY` VARCHAR(30) NULL       -- 수정일자\n" +
                ");";
        DbService.dbCreate(sql);
    }

    //북마크 목록 추가
    public static void dbBookmarkListCreate() {
        String sql = "CREATE TABLE `BOOKMARKLIST` (\n" +
                "\t`LIST_ID`        INTEGER     primary key autoincrement, -- ID\n" +
                "\t`BM_NAME`   VARCHAR(50) NULL , -- 북마크이름\n" +
                "\t`WIFI_NAME` VARCHAR(50) NULL , -- 와이파이이름\n" +
                "\t`LIST_REG_DAY`   DATE        NULL , -- 등록일자\n" +
                "\t`GROUP_ID`  VARCHAR(50) NULL -- 북마크그룹이름\n" +
                ");";
        DbService.dbCreate(sql);
    }

    //북마크 그룹 추가
    public static void bookmarkGroupAdd(String groupName, int groupOrder) {
        String sql = "insert into BOOKMARKGROUP(group_name, GROUP_ORDER, reg_day, change_day)\n" +
                "values('" + groupName + "', '" + groupOrder + "',datetime('now', 'localtime'), '') ;";
        DbService.bookmarkGroupAdd(sql);
    }

    public static void bookmarkGroupListAdd(String bookmarkName, String wifiName) {
        String sql = "insert into BOOKMARKLIST(BM_NAME, WIFI_NAME, LIST_REG_DAY, GROUP_ID)\n" +
                "VALUES ('"+bookmarkName+"', '"+wifiName+"', datetime('now', 'localtime'), '"+bookmarkName+"')";
        DbService.insert(sql);
    }

    public static List<BookmarkListMember> getBookmarkListMember(){
        String sql = "select * from BOOKMARKLIST\n" +
                "order by LIST_REG_DAY asc;";

        return DbService.getBookmarkListMember(sql);
    }

    //북마크 목록 가져오기
    public static List<BookmarkGroupMember> getBookmarkMember() {
        String sql = "select * from BOOKMARKGROUP\n" +
                "order by GROUP_ORDER asc;";

        return DbService.getBookmarkGroupMember(sql);
    }

    public static void bookmarkEdit(String groupName, String preGroupName, int groupOrder, int preGroupOrder) {
        String sql = "update BOOKMARKGROUP\n" +
                "set GROUP_NAME = '" + groupName + "', GROUP_ORDER=" + groupOrder + ", CHANGE_DAY = datetime('now', 'localtime')\n" +
                "where GROUP_NAME = '" + preGroupName + "' and GROUP_ORDER="+preGroupOrder;
        DbService.dbUpdate(sql);

        String bookmarkListSql = "update BOOKMARKLIST\n" +
                "set BM_NAME = '"+groupName+"'\n" +
                "where BM_NAME = '"+preGroupName+"';";
        DbService.dbUpdate(bookmarkListSql);



    }

    public static void bookmarkDelete(String groupName) {
        String sql = "delete\n" +
                "from BOOKMARKGROUP\n" +
                "where GROUP_NAME ='" + groupName + "';";
        DbService.dbDelete(sql);

        String listSql = "delete from BOOKMARKLIST\n" +
                "where BM_NAME = '" + groupName + "';";
        DbService.dbDelete(listSql);

    }

    public static void bookmarkListDelete(int id) {
        String sql = "delete\n" +
                "from BOOKMARKLIST\n" +
                "where LIST_ID="+id;
        DbService.dbDelete(sql);
    }
    //-----------------------------------------------------------

    //WIFIINFO 테이블 생성
    public static void dbWifiInfoCreate() {
        String sql = "CREATE TABLE `WIFIINFO` (\n" +
                "\t`distance`            DOUBLE , -- 거리(KM)\n" +
                "\t`X_SWIFI_MGR_NO`      VARCHAR(255)  primary key, -- 관리번호\n" +
                "\t`X_SWIFI_WRDOFC`      VARCHAR(255) ,  -- 자치구\n" +
                "\t`X_SWIFI_MAIN_NM`     VARCHAR(255) , -- 와이파이명\n" +
                "\t`X_SWIFI_ADRES1`      VARCHAR(255) , -- 도로명주소\n" +
                "\t`X_SWIFI_ADRES2`      VARCHAR(255) , -- 상세주소\n" +
                "\t`X_SWIFI_INSTL_FLOOR` VARCHAR(255) , -- 설치위치(층)\n" +
                "\t`X_SWIFI_INSTL_TY`    VARCHAR(255) , -- 설치유형\n" +
                "\t`X_SWIFI_INSTL_MBY`   VARCHAR(255) , -- 설치기관\n" +
                "\t`X_SWIFI_SVC_SE`      VARCHAR(255) , -- 서비스구분\n" +
                "\t`X_SWIFI_CMCWR`       VARCHAR(255) , -- 망종류\n" +
                "\t`X_SWIFI_CNSTC_YEAR`  INTEGER      , -- 설치년도\n" +
                "\t`X_SWIFI_INOUT_DOOR`  VARCHAR(255) , -- 실내외구분\n" +
                "\t`X_SWIFI_REMARS3`     VARCHAR(255) , -- WIFI접속환경\n" +
                "\t`LAT`                 DOUBLE      , -- Y좌표\n" +
                "\t`LNT`                 DOUBLE      , -- X좌표\n" +
                "\t`WORK_DTTM`           VARCHAR(255)          -- 작업일자\n" +
                ");\n";
        DbService.dbCreate(sql);
    }

    //HISTORY 테이블 생성
    public static void dbHistoryCreate() {
        String sql = "CREATE TABLE `HISTORY`(\n" +
                "    LAT INTEGER,\n" +
                "    LNT INTEGER,\n" +
                "    NowDate VARCHAR(255)" +
                ");";
        DbService.dbCreate(sql);

    }

    public static void dbWifiDataInsert() throws IOException {
        GetJson.jsonInsertData();
    }


    public static void dropTable(String tableName) {
        DbService.dropTable(tableName);
        System.out.println("drop table : " + tableName);
    }

    public static List<WifiMember> dbWifiMemberList() {
        return DbService.dbList();
    }

    public static List<HistoryMember> dbHistoryList() {
        return DbService.dbHistoryList();
    }

    public static WifiMember getDetail(){
        return DbService.dbDetail(db.detailName);
    }

    //거리에 비례하여 저장
    public static void distanceInsert(double x, double y) {
        DbService.dbHistory(x, y); //히스토리에도 저장
    }

    public static void dbDelete(String dataName) {
        String sql = "delete from history " +
                "where NowDate = \'" + dataName + "\';";
        DbService.dbDelete(sql);
    }

}
