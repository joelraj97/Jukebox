package org.example.DAOInterfaces;

public interface UserInterface
{
    String createNewUser(String username,String password);
    String existingUser(String username,String password);
}
