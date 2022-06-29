package uz.epam.webproject.controller.command;

import uz.epam.webproject.controller.command.impl.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand()),
    FIND_ALL_MEDICINE(new FindAllMedicineCommand()),
    FIND_MEDICINE_BY_QUERY(new FindMedicineByQueryCommand()),
    ADD_MEDICINE(new AddMedicineCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    FIND_ALL_ORDERS(new FindAllOrdersCommand()),
    SELECT_ORDERS_BY_ID(new SelectOrdersByIdCommand()),
    DELETE_MEDICINE(new DeleteMedicineCommand()),
    DELETE_USER(new DeleteUserCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    FIND_USER_TO_UPDATE(new FindUserToUpdateCommand()),
    FIND_USER_INFO(new FindUserInfoCommand()),
    FIND_MEDICINE_TO_UPDATE(new FindMedicineToUpdateCommand()),
    FIND_MEDICINE_INFO(new FindMedicineInfoCommand()),
    UPDATE_MEDICINE(new UpdateMedicineCommand()),
    ADD_MEDICINE_TO_BASKET(new AddMedicineToBasketCommand()),
    REMOVE_MEDICINE_FROM_BASKET(new RemoveMedicineFromBasketCommand()),
    CHANGE_MEDICINE_QUANTITY(new ChangeMedicineQuantityCommand()),
    ORDER_MEDICINE(new OrderMedicineCommand()),
    DEFAULT(new DefaultCommand());


    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command of(String strCommand){
            CommandType current;

        if (strCommand == null) {
            current = CommandType.DEFAULT;
            return current.command;
        }
            try {
                current = CommandType.valueOf(strCommand.toUpperCase());
                return current.command;
            }catch (IllegalArgumentException e){
                current = CommandType.DEFAULT;
               return current.command;
            }

    }

    public Command getCommand() {
        return command;
    }
}
