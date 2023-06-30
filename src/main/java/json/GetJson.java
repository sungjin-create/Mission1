package json;

import db.DbService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetJson {


    public static void jsonInsertData() throws IOException {
        int total = (int) getTotal(); //총 개수 가져오기

        int count = total / 1000;
        for (int i = 0; i < count; i++) {
            DbService.dbInsert(i * 1000 + 1, (i + 1) * 1000);
            System.out.println("저장완료" + i);
        }
        DbService.dbInsert(count * 1000 + 1, total);
    }

    public static long getTotal() throws IOException {
        String urlText;
        String result;
        long totalCount = 0L;
        urlText = "http://openapi.seoul.go.kr:8088/584b4b785373756e36337570717a6d/json/TbPublicWifiInfo/1/2";
        try {
            URL url = new URL(urlText);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = br.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject jsonObject_TPWI = (JSONObject) jsonObject.get("TbPublicWifiInfo");

            totalCount = (long) jsonObject_TPWI.get("list_total_count");
        } catch (Exception e) {
            System.out.println("error getTotal");
        }
        return totalCount;
    }

//    json 하나하나씩 넣어주는 방법은 너무 오래걸린다

//    public void insertData(int start, int end) throws IOException {
//        String result;
//        String address = "http://openapi.seoul.go.kr:8088/";
//        String key = "584b4b785373756e36337570717a6d/";
//        String dataType = "json/";
//        String infoName = "TbPublicWifiInfo/";
//        StringBuilder sb = new StringBuilder();
//        sb.append(address);
//        sb.append(key);
//        sb.append(dataType);
//        sb.append(infoName);
//        sb.append(start + "/");
//        sb.append(end);
//        try {
//            URL url = new URL(sb.toString());
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.setRequestProperty("Content-type", "application/json");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//            result = br.readLine();
//
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
//            JSONObject jsonObject_TPWI = (JSONObject) jsonObject.get("TbPublicWifiInfo");
//            JSONArray jsonArray = (JSONArray) jsonObject_TPWI.get("row");
//            DbService dbService = new DbService();
//
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject object = (JSONObject) jsonArray.get(i);
//                String X_SWIFI_MGR_NO = (String) object.get("X_SWIFI_MGR_NO");
//                String X_SWIFI_WRDOFC = (String) object.get("X_SWIFI_WRDOFC");
//                String X_SWIFI_MAIN_NM = (String) object.get("X_SWIFI_MAIN_NM");
//                String X_SWIFI_ADRES1 = (String) object.get("X_SWIFI_ADRES1");
//                String X_SWIFI_ADRES2 = (String) object.get("X_SWIFI_ADRES2");
//                String X_SWIFI_INSTL_FLOOR = (String) object.get("X_SWIFI_INSTL_FLOOR");
//                String X_SWIFI_INSTL_TY = (String) object.get("X_SWIFI_INSTL_TY");
//                String X_SWIFI_INSTL_MBY = (String) object.get("X_SWIFI_INSTL_MBY");
//                String X_SWIFI_SVC_SE = (String) object.get("X_SWIFI_SVC_SE");
//                String X_SWIFI_CMCWR = (String) object.get("X_SWIFI_CMCWR");
//                int X_SWIFI_CNSTC_YEAR = Integer.parseInt((String) object.get("X_SWIFI_CNSTC_YEAR"));
//                String X_SWIFI_INOUT_DOOR = (String) object.get("X_SWIFI_INOUT_DOOR");
//                String X_SWIFI_REMARS3 = (String) object.get("X_SWIFI_REMARS3");
//                double LAT = Double.parseDouble((String) object.get("LAT"));
//                double LNT = Double.parseDouble((String) object.get("LNT"));
//                String WORK_DTTM = (String) object.get("WORK_DTTM");
//                dbService.dbInsert(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM,
//                        X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY,
//                        X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR,
//                        X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM);
//            }
//        } catch (Exception e) {
//            System.out.println("error");
//        }
//    }
}
