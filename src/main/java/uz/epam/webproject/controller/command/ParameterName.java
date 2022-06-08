package uz.epam.webproject.controller.command;

public enum ParameterName {
        ;
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PAGES_PATH = "/config/page";
    public static final String COMMAND = "command";
    public static final String ERROR_MESSAGE_SIGN_IN = "error_message";
    public static final String USER = "user";
    public static final String USERS = "users";

    /*
    * page parameters
    * */
    public static final String HOME_PAGE = "/pages/home.jsp";
    public static final String INDEX_PAGE = "/";
    public static final String USERS_PAGE = "/pages/users.jsp";
    public static final String REGISTRATION_PAGE = "/registration.jsp";
    public static final String LIST_OF_USERS = "/pages/list-users.jsp";
    public static final String ERROR_500_PAGE = "/pages/error/error_500.jsp";
    public static final String ERROR_404_PAGE = "/pages/error/error_404.jsp";

    /*
    * Session attributes
    * */
    public static final String LOCALE = "locale";
    public static final String CURRENT_PAGE = "current_page";
    public static final String AUTHORIZED_USER = "authUser";
    public static final String LANGUAGE = "language";
    public static final String ROLE = "role";

    /*
    * error messages
    * */
    public static final String UNAVAILABLE_EMAIL_ADDRESS = "unavailable_email_address";
    public static final String UNAVAILABLE_LOGIN = "unavailable_login";
}
