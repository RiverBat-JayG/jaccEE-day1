package edu.acc.java3.login;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDaoImpl dao = new UserDaoImpl();
        String u1user = sce.getServletContext().getInitParameter("user.1.name");
        String u1pass = sce.getServletContext().getInitParameter("user.1.pass");
        String u2user = sce.getServletContext().getInitParameter("user.2.name");
        String u2pass = sce.getServletContext().getInitParameter("user.2.pass");
        User u1 = new User(u1user, u1pass);
        User u2 = new User(u2user, u2pass);
        dao.addUser(u1);
        dao.addUser(u2);
        sce.getServletContext().setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
