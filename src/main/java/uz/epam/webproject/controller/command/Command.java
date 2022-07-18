package uz.epam.webproject.controller.command;

import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.user.User;
import uz.epam.webproject.entity.user.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface Command {

    Router execute(HttpServletRequest request) throws CommandException;

    default boolean isPharmacist(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.PHARMACIST);
    }

    default boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }

    default boolean isDoctor(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.DOCTOR);
    }
}
