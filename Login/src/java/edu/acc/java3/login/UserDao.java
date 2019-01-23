package edu.acc.java3.login;

public interface UserDao {
    public void addUser(User u);
    public boolean validate(User u);
    public boolean authenticate(User u);
}
