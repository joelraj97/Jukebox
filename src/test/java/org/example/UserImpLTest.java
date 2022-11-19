package org.example;
import org.example.DAOImplClasses.PlayListImpl;
import org.example.DAOImplClasses.UserImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserImpLTest
{
    UserImpl userobj=null;
    @BeforeEach
    public void setUp()
    {
        userobj=new UserImpl();
    }
    @AfterEach
    public void tearDown()
    {
        userobj=null;
    }
    @Test
    public void existingUserTest()
    {
       assertEquals("Invalid Username or Password",userobj.existingUser("hugo","hugolloris"));
    }
    @Test
    public void createnewUserTest()
    {
        assertEquals("Username Already Exists",userobj.createNewUser("harrykane","kane10"));
    }


}
