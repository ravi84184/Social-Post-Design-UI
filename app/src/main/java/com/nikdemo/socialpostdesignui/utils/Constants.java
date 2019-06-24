package com.nikdemo.socialpostdesignui.utils;

/**
 * Created by nehal on 22/11/18.
 */
public
class Constants {


    public static final String TOKEN = "token";
    public static final String USER_ID = "user_id";
    public static final String USER_PROFILE = "user_profile";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_MOBILE = "user_mobile";
    public static final String USER_NAME = "user_name";
    public static final String USER_TYPE = "user_type";
    public static final String USER_STATE_ID = "state_id";
    public static final String USER_CITY_ID = "city_id";
    public static final String USER_SCHOOL_ID = "school_id";
    public static final String USER_INSTRUCTOR_ID = "instructor_id";
    public static final String USER_SPORT_ID = "sport_id";
    // Fonts
    public static String sourceLatoRegular= "fonts/Lato-Regular.ttf";
    public static String sourceLatoBold = "fonts/Lato-Bold.ttf";
    public static String sourceTitilliumRegular = "fonts/Titillium-Regular.otf";
    public static String sourceTitilliumBold = "fonts/Titillium-Bold.otf";


    public static final String MAIN_URL = "http://192.168.9.198/sports/api/";

    public static final String LOGIN_API_NAME = MAIN_URL + "login";
    public static final String STATE_API_NAME = MAIN_URL + "state";
    public static final String CITY_API_NAME = MAIN_URL + "city";
    public static final String PROFILE_GET_API_NAME = MAIN_URL + "user/profile";
    public static final String PROFILE_UPDATE_API_NAME = MAIN_URL + "user/profile/update";
    public static final String SCHOOL_CITY_API_NAME = MAIN_URL + "citywiseschool";
    public static final String SPORT_API_NAME = MAIN_URL + "sports";
    public static final String EQUIPMENT_API_NAME = MAIN_URL + "sportequipment";
    public static final String EQUIPMENT_CREATE_API_NAME = MAIN_URL + "equipmentpminstructor/create";
    public static final String SCHOOL_LIST_API_NAME = MAIN_URL + "school";
    public static final String TOURNAMENT_LEVEL_API_NAME = MAIN_URL + "tournament/level";
    public static final String TOURNAMENT_CREATE_API_NAME = MAIN_URL + "tournament/create";
    public static final String TOURNAMENT_LIST_API_NAME = MAIN_URL + "tournament";
    public static final String TOURNAMENT_DELETE_API_NAME = MAIN_URL + "tournament/delete";
    public static final String TOURNAMENT_DETAILS_API_NAME = MAIN_URL + "tournament/view";
    public static final String TOURNAMENT_PARTICIPATE_LIST_API_NAME = MAIN_URL + "tournament/participate/list";
    public static final String TOURNAMENT_PARTICIPATE_DELETE_API_NAME = MAIN_URL + "tournament/participate/delete";
    public static final String SCHOOL_CLASS_API_NAME = MAIN_URL + "schoolwiseclass";
    public static final String MEDAL_API_NAME = MAIN_URL + "medal";
    public static final String PARTICIPATES_ADD_API_NAME = MAIN_URL + "tournament/participates";
    public static final String PARTICIPATES_UPDATE_API_NAME = MAIN_URL + "tournament/participate/update";
    public static final String TOURNAMENT_DATA_API_NAME = MAIN_URL + "tournament/view";
    public static final String TOURNAMENT_UPDATE_API_NAME = MAIN_URL + "tournament/update";
    public static final String LEVELS_API_NAME = MAIN_URL + "levels";
    public static final String LESSON_PLAN_API_NAME = MAIN_URL + "lessonplan/list";
    public static final String ASSESSMENT_NAME_LIST_API_NAME = MAIN_URL + "assessment/names";
    public static final String ASSESSMENT_LIST_API_NAME = MAIN_URL + "assessment/view";
    public static final String STUDENT_LIST_API_NAME = MAIN_URL + "students";
    public static final String ASSESSMENT_ADD_API_NAME = MAIN_URL + "assessment/store";
    public static final String ATTENDANCE_STUDENT_API_NAME = MAIN_URL + "student/attendance";
    public static final String FORGET_PASSWORD_API_NAME = MAIN_URL + "forgotpassword";
    public static final String FORGET_PASSWORD_RESET_API_NAME = MAIN_URL + "forgotpassword/reset";
    public static final String ATTENDANCE_ADD_API_NAME = MAIN_URL + "student/attendance/store";
    public static final String CHANGE_PASSWORD_API_NAME = MAIN_URL + "changepassword";
    public static final String SCHEDULE_LIST_API_NAME = MAIN_URL + "calendordata";
    public static final String SPORT_ACTIVITY_API_NAME = MAIN_URL + "activity";
    public static final String TOURNAMENT_API_NAME = MAIN_URL + "instructor/tournamentnames";
    public static final String LECTURE_ATTENDANCE_API_NAME = MAIN_URL + "lecture/attendance";


    public static final int LOGIN_API_REQ = 1;
    public static final int STATE_API_REQ = 2;
    public static final int CITY_API_REQ = 3;
    public static final int PROFILE_GET_API_REQ = 4;
    public static final int PROFILE_UPDATE_API_REQ = 5;
    public static final int SCHOOL_CITY_API_REQ = 6;
    public static final int SPORT_API_REQ = 8;
    public static final int EQUIPMENT_API_REQ = 9;
    public static final int EQUIPMENT_CREATE_API_REQ = 10;
    public static final int SCHOOL_LIST_API_REQ = 11;
    public static final int TOURNAMENT_LEVEL_API_REQ = 12;
    public static final int TOURNAMENT_CREATE_API_REQ = 13;
    public static final int TOURNAMENT_LIST_API_REQ = 14;
    public static final int TOURNAMENT_DELETE_API_REQ = 15;
    public static final int TOURNAMENT_DETAILS_API_REQ = 16;
    public static final int TOURNAMENT_PARTICIPATE_LIST_API_REQ = 17;
    public static final int TOURNAMENT_PARTICIPATE_DELETE_API_REQ = 18;
    public static final int SCHOOL_CLASS_API_REQ = 19;
    public static final int MEDAL_API_REQ = 20;
    public static final int PARTICIPATES_ADD_API_REQ = 21;
    public static final int PARTICIPATES_UPDATE_API_REQ = 22;
    public static final int TOURNAMENT_DATA_API_REQ = 23;
    public static final int TOURNAMENT_UPDATE_API_REQ = 24;
    public static final int LEVELS_API_REQ = 25;
    public static final int LESSON_PLAN_API_REQ = 26;
    public static final int ASSESSMENT_NAME_LIST_API_REQ = 27;
    public static final int ASSESSMENT_LIST_API_REQ = 28;
    public static final int STUDENT_LIST_API_REQ = 29;
    public static final int ASSESSMENT_ADD_API_REQ= 30;
    public static final int ATTENDANCE_STUDENT_API_REQ= 30;
    public static final int FORGET_PASSWORD_API_REQ= 31;
    public static final int FORGET_PASSWORD_RESET_API_REQ = 32;
    public static final int ATTENDANCE_ADD_API_REQ = 33;
    public static final int CHANGE_PASSWORD_API_REQ = 34;
    public static final int SCHEDULE_LIST_API_REQ = 35;
    public static final int SPORT_ACTIVITY_API_REQ = 36;
    public static final int TOURNAMENT_API_REQ = 37;
    public static final int LECTURE_ATTENDANCE_API_REQ = 38;


}
