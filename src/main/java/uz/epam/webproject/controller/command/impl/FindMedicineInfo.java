package uz.epam.webproject.controller.command.impl;

import uz.epam.webproject.controller.command.Command;
import uz.epam.webproject.controller.command.Router;
import uz.epam.webproject.controller.command.exception.CommandException;
import uz.epam.webproject.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FindMedicineInfo implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }

    @Override
    public boolean isPharmacist(HttpSession session) {
        return false;
    }


    @Override
    public boolean isAdmin(HttpSession session) {
        return false;
    }

    @Override
    public boolean isDoctor(HttpSession session) {
        return false;
    }
}
