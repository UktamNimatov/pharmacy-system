package uz.epam.webproject.controller.command;

public enum ParameterName {
    ;
    /*
     *user parameter
     *  */
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CURRENT_PASSWORD = "current_password";
    public static final String EMAIL = "email";
    public static final String PAGES_PATH = "/config/page";
    public static final String COMMAND = "command";
    public static final String ERROR_MESSAGE_SIGN_IN = "error_message";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String NEW_PASSWORD = "new_password";
    public static final String CONFIRM_NEW_PASSWORD = "confirm_new_password";
    public static final String LOGIN = "login";
    public static final String USER_DELETED = "user_deleted";
    public static final String USER_NOT_DELETED = "user_not_deleted";
    public static final String UPDATED_USER = "updated_user";
    public static final String USER_ID = "user_id";
    public static final String TEMPORARY_USER = "temp_user";

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
    * bootstrap pages
    * */
    public static final String BOOTSTRAP_REGISTRATION_PAGE = "/startbootstrap/register.jsp";
    public static final String BOOTSTRAP_HOME_PAGE = "/startbootstrap/home.jsp";
    public static final String REGISTRATION_CONFIRMATION_PAGE = "/startbootstrap/registration-confirmation-page.jsp";
    public static final String BOOTSTRAP_USERS_LIST_TABLE = "/startbootstrap/user-table.jsp";
    public static final String BOOTSTRAP_MEDICINE_LIST_TABLE = "/startbootstrap/medicine-table.jsp";
    public static final String BOOTSTRAP_PROFILE_PAGE = "/startbootstrap/profile.jsp";
    public static final String BOOTSTRAP_CLIENT_PROFILE_PAGE = "/startbootstrap/client-profile.jsp";
    public static final String BOOTSTRAP_CLIENT_INFO_PAGE = "/startbootstrap/client-info.jsp";
    public static final String BOOTSTRAP_PASSWORD_CHANGE = "/startbootstrap/password-change.jsp";

    public static final String BOOTSTRAP_MEDICINE_INFO_PAGE = "/startbootstrap/medicine-info.jsp";
    public static final String BOOTSTRAP_MEDICINE_PROFILE_PAGE = "/startbootstrap/medicine-profile.jsp";
    public static final String BOOTSTRAP_ORDER_MEDICINE_PAGE = "/startbootstrap/order-medicine.jsp";

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
    public static final String INVALID_CERTIFICATE_NUMBER = "invalid_certificate_number";
    public static final String FAILED_TO_UPDATE_MEDICINE = "medicine_update_operation_failed";

    /*
    * operation
    * */
    public static final String OPERATION_MESSAGE = "operation_message";
    public static final String PASSWORD_CHANGE_MESSAGE = "password_change_message";

    /*
    * medicine parameters
    * */
    public static final String MEDICINE_ID = "medicine_id";
    public static final String MEDICINE_LIST = "medicine_list";
    public static final String MEDICINE_LIST_BY_QUERY = "medicine_list_by_query";
    public static final String MEDICINE_TITLE = "title";
    public static final String MEDICINE_PRICE = "price";
    public static final String MEDICINE_DESCRIPTION = "description";
    public static final String WITH_PRESCRIPTION = "with_prescription";
    public static final String MEDICINE_CREATED = "medicine_created";
    public static final String MEDICINE_NOT_CREATED = "medicine_not_created";
    public static final String MEDICINE_SEARCH_QUERY = "medicine_search_query";
    public static final String MEDICINE_DELETED = "medicine_deleted";
    public static final String MEDICINE_NOT_DELETED = "medicine_not_deleted";
    public static final String TEMPORARY_MEDICINE = "temp_medicine";
    public static final String UPDATED_MEDICINE = "updated_medicine";
    public static final String MEDICINE_BASKET = "medicine_basket";

    public static final String MEDICINE_QUANTITY_MAP = "medicine_quantity_map";


    /*
    * Order parameters
    * */
    public static final String ORDERS = "orders";
    public static final String ORDER_ID = "order_id";
    public static final String ORDERS_BY_USER = "orders_by_user";
    public static final String SUM = "sum";
    public static final String TRANSACTION_COST = "transaction_cost";
    public static final String TOTAL_COST = "total_cost";


    public static final String MEDICINES_OF_ONE_ORDER = "medicines_of_one_order";

    /*
    * permission parameters
    * */
    public static final String NO_PERMISSION = "no permission";
}
