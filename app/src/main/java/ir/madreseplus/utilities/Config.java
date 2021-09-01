package ir.madreseplus.utilities;

public class Config {

    public static final String baseUrl = "https://madreseplus.com/api/";
    public static final int REGISTERED = 102;
    public static final int UNAUTHORIZED = 401;
    public static final int COMPLEMENT_REGISTER = 104;
    public static final int CONFIRM_CODE_ERROR = 464;
    public static final int REGISTER_CONFIRM_CODE = 103;
    public static final String TOKEN_KEY = "token";
    public static final String ID_KEY = "id";
    public static final String USER_MOBILE_KEY = "mobile";
    public static final String USER_GENDER_KEY = "gender";
    public static final int FIRST_TIME_LAUNCH = 0;
    public static final String START_TIME_KEY = "start_time";
    public static final String TICKET_ID_KEY = "ticket_id";
    public static final String END_TIME_KEY = "end_time";
    public static final String DAY_KEY = "day";
    public static final String MONTH_KEY = "month";
    public static final String YEAR_KEY = "year";
    public static final String DAILY_KEY = "daily";

    //ERROR_400_MESSAGES_FA
    public static final int INTERNAL_ERROR = 0x00; // خطای داخلی
    public static final int INFORMATION_ERROR = 0x10; // اطلاعات ارسالی اشتباه است
    public static final int PHONE_NUMBER_ERROR = 0x11; //شماره همراه اشتباه است
    public static final int USERNAME_OR_PASS_ERROR = 0x12; //نام کاربری یا گذرواژه اشتباه است
    public static final int LOGIN_ERROR = 0x20; //امکان ورود برای شما وجود ندارد
    public static final int IDENTITY_ERROR = 0x21; //هویت شما نامعتبر است
    public static final int CONFIRMATION_CODE_INVALID = 0x23; //کد تایید منقضی شده است. لطفا ارسال دوباره را انتخاب نمایید
    public static final int CONFIRMATION_CODE_ERROR = 0x24; //کد تایید اشتباه است





}
