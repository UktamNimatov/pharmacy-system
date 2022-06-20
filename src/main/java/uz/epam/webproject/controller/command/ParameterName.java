package uz.epam.webproject.controller.command;

public enum ParameterName {
    ;
    /*
     *user parameter
     *  */
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PAGES_PATH = "/config/page";
    public static final String COMMAND = "command";
    public static final String ERROR_MESSAGE_SIGN_IN = "error_message";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String NEW_PASSWORD = "new_password";
    public static final String LOGIN = "login";
    public static final String USER_DELETED = "user_deleted";
    public static final String USER_NOT_DELETED = "user_not_deleted";

    /*
     * page parameters
     * */
    public static final String HOME_PAGE = "/pages/home.jsp";
    public static final String INDEX_PAGE = "/";
    public static final String USERS_PAGE = "/pages/users.jsp";
    public static final String REGISTRATION_PAGE = "/pages/registration.jsp";
    public static final String LIST_OF_USERS = "/pages/list-users.jsp";
    public static final String PASSWORD_UPDATE_PAGE = "/pages/password-update.jsp";
    public static final String ERROR_500_PAGE = "/pages/error/error_500.jsp";
    public static final String ERROR_404_PAGE = "/pages/error/error_404.jsp";
    public static final String LIST_OF_MEDICINES_PAGE = "/pages/list-medicine.jsp";
    public static final String MEDICINE_CREATED_PAGE = "/pages/medicine-created.jsp";
    public static final String MEDICINE_LIST_BY_QUERY_PAGE = "/pages/medicine-list-by-query.jsp";
    public static final String NEW_LIST_OF_USERS_PAGE = "/pages/new-list-users.jsp";
    public static final String NEW_LIST_OF_MEDICINES_PAGE = "/pages/new-list-medicine.jsp";
    public static final String SIDEBAR_PAGE = "/pages/sidebar.jsp";
    public static final String LIST_OF_ORDERS_PAGE = "/pages/list-orders.jsp";

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

    /*
    * operation
    * */
    public static final String PASSWORD_CHANGE_MESSAGE = "password_change_message";

    /*
    * medicine parameters
    * */
    public static final String MEDICINE_LIST = "medicine_list";
    public static final String MEDICINE_LIST_BY_QUERY = "medicine_list_by_query";
    public static final String MEDICINE_TITLE = "title";
    public static final String MEDICINE_PRICE = "price";
    public static final String MEDICINE_DESCRIPTION = "description";
    public static final String WITH_PRESCRIPTION = "with_prescription";
    public static final String MEDICINE_CREATED = "medicine_created";
    public static final String MEDICINE_SEARCH_QUERY = "medicine_search_query";
    public static final String MEDICINE_DELETED = "medicine_deleted";
    public static final String MEDICINE_NOT_DELETED = "medicine_not_deleted";

    /*
    * Order parameters
    * */
    public static final String ORDERS = "orders";
}
