package qvm.m.appcuahang.ultil;

import qvm.m.appcuahang.Model.User;

public class Server {
    public static String localhost = "192.168.31.150";
    public static String DuongDanLoaiSp = "http://" + localhost + "/server/getloaisp.php";
    public static String DuongDanSpNew = "http://" + localhost + "/server/getsanphammoinhat.php";
    public static String DuongDanSpSX = "http://" + localhost + "/server/getsanpham.php";
    public static final String BASE_URL = "http://192.168.31.150/server/";
    public static User user_current = new User();
}
