package com.rceroorkee.indoriya.gagan.rce;


/**
 * Created by Belal on 10/24/2015.
 */
public class Config {


    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://rceroorkee.net16.net/rcenoticeapp/addNotice.php";
    public static final String URL_GET_ALL = "http://rceroorkee.net16.net/rcenoticeapp/getAllNotice.php";
    public static final String URL_GET_EMP = "http://rceroorkee.net16.net/rcenoticeapp/getNotice.php";
    public static final String URL_UPDATE_EMP = "http://rceroorkee.net16.net/rcenoticeapp/updateNotice.php";
    public static final String URL_DELETE_EMP = "http://rceroorkee.net16.net/rcenoticeapp/deleteNotice.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_NOTICE_SUB = "sub";
    public static final String KEY_NOTICE_MSG = "msg";
//    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_SUB = "sub";
    public static final String TAG_MSG = "msg";
//    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String NOTICE_ID = "notice_id";

    public static final String NOTICE_SUB = "notice_sub";

    public static final String NOTICE_MSG = "notice_msg";
}