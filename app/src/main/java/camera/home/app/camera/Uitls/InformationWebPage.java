package camera.home.app.camera.Uitls;

/**
 * Created by Hai on 11/2/2016.
 */
public class InformationWebPage {
    // ip capsule
    public  static  final String HTTP = "http://";
    public static  final  String PRINT_CAPSULE =":8080/cmd?print";
    public static  final  String CAP_ON =":8080/cmd?capsule=on";
    public static  final  String CAP_OFF =":8080/cmd?capsule=off";
    public static String IP_CAMERA =":8080/cmd?ip=";

    public static  final  String IP_SUBFIX =":8080/cmd?subfix=";
    public static  final  String IP_MODE =":8080/cmd?mode=";


    public static String CMD_PRINT = "http://"+InformationWebPage.IP+":8080/cmd?print";
    public static String CAPSULE_ON = "http://192.168.2.103:8080/cmd?capsule=on";
    public static String CAPSULE_OFF = "http://192.168.2.103:8080/cmd?capsule=off";
    // camera
    public static String IP_CMR = "http://192.168.2.103:8080/cmd?ip=";
    public static String CAPSULE_MODE_SELECT = "http://192.168.2.103:8080/cmd?mode=";
    public static String CAPSULE_SUBFIX_SELECT = "http://" + InformationWebPage.IP + ":8080/cmd?subfix=";
    public static final String CAPSULE_STATUS = "capsule_status";

    public static final String CAPSULE_MODE = "capsule_mode";
    public static final String CAMERA_SUBFIX = "camera_subfix";
    public static String CONTENT;
    public static String IP = "";
    public static String LINK_CAMERA = "camera_link";

    public static final String SUB = ":8080/cmd?sub_subfix=";
    public static String SUB_SUB = "";

    public static String CAMERA_IP = "camera_ip";
}
