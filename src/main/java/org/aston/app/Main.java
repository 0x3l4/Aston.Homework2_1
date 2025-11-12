package org.aston.app;

import org.aston.model.User;
import org.aston.ui.ConsoleMenu;
import org.aston.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        ConsoleMenu menu = new ConsoleMenu();
        menu.start();
    }
}