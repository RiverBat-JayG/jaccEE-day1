package edu.acc.java3.login;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserDaoImpl implements UserDao {
    
    private static final Pattern USER_PATT = Pattern.compile("^\\w{6,12}$");
    private static final Pattern PASS_PATT = Pattern.compile("^[\\w\\.-]{8,16}$");
    
    private final List<User> users = new ArrayList<>();
    
    @Override
    public void addUser(User u) {
        users.add(u);
    }

    @Override
    public boolean validate(User u) {
        if (u.getUser() == null || u.getPass() == null)
            return false;
        if (!USER_PATT.matcher(u.getUser()).matches())
            return false;
        return PASS_PATT.matcher(u.getPass()).matches();
    }

    @Override
    public boolean authenticate(User u) {
        for (User user : users)
            if (u.getUser().equals(user.getUser()) &&
                    u.getPass().equals(user.getPass()))
                return true;
        return false;
    }
    
}
