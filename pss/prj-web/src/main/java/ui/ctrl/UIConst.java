package ui.ctrl;

public class UIConst {
    /**URL子路径**/
    public final static String AREANAME = "area00" ;

    /**页面路径**/
    public final static String VIEWNAME="view00";;

    /**URL全路径**/
    public final static String AREAPATH="/"+AREANAME;

    /**页面根**/
    public final static String VIEWROOT="/WEB-INF";

    /**页面全路径**/
    public final static String VIEWPATH=VIEWROOT+"/"+VIEWNAME;

    /**后台登录账号的会话key**/
    public final static String BG_LOGINUSER_KEY="BG_LOGINUSER_KEY";

    /**前台登录账号的会话key:会员...**/
    public final static String FG_LOGINUSER_KEY="FG_LOGINUSER_KEY";

    /**其它登录账号的会话key**/
    public final static String OTHER_LOGINUSER_KEY="OTHER_LOGINUSER_KEY";

    /**后台登录账户是否管理员的会话key**/
    public final static String BG_ISADMIN_KEY="BG_ISADMIN_KEY";

    /**后台验证码的会话key**/
    public final static String BG_VALLDATE_CODE_KEY="BG_VALLDATE_CODE_KEY";
}
