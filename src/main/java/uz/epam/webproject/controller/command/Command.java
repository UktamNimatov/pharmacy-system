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

    default boolean isPharmacist(HttpSession session){
        return getAuthUser(session).getRole().equals(UserRole.PHARMACIST); }

    default User getAuthUser(HttpSession session){
        return (User) session.getAttribute(ParameterName.AUTHORIZED_USER); }

        default boolean isAdmin(HttpSession session){
        return getAuthUser(session).getRole().equals(UserRole.ADMIN);
        }

    default boolean isDoctor(HttpSession session){
        return getAuthUser(session).getRole().equals(UserRole.DOCTOR);
    }
}
