package common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class db {
    public static String url = "jdbc:sqlite:wifi.sqlite";
    public static String classForName = "org.sqlite.JDBC";

    public static double latitude;
    public static double longitude;
    public String wifiName;

    public static String detailName;


}
