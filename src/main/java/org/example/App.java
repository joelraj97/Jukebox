package org.example;

import org.example.DAOImplClasses.UserImpl;
import org.example.DAOInterfaces.UserInterface;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Jukebox");
        boolean mainstatus = false;
        do {
            System.out.println("1.New user\n2.Existing User");
            int userchoice = scan.nextInt();
            if (userchoice == 1) {
                mainstatus = false;
                String username="";
                String password="";
                boolean status = false;
                do {
                    boolean usernamestatus=false;
                    do {
                        System.out.println("Enter username");
                         username = scan.next();
                        if(username.length()<=3)
                        {
                            System.out.println("Username should be min 4 characters");
                            usernamestatus=true;
                        }
                        else
                        {
                            usernamestatus=false;
                        }
                    }while(usernamestatus);
                     boolean passwordstatus=false;
                    do {

                        System.out.println("Enter Password");
                         password = scan.next();
                        if(password.length()<=3)
                        {
                            System.out.println("password should be min 4 Characters");
                            passwordstatus=true;
                        }
                        else
                        {
                            passwordstatus=false;
                        }
                    }while(passwordstatus);
                    UserInterface u = new UserImpl();
                    String userstatus = u.createNewUser(username, password);
                    if (userstatus.equalsIgnoreCase("Username Already Exists")) {
                        status = true;
                    } else {
                        status = false;
                    }
                } while (status);
            }
        else if (userchoice == 2)
        {
            String username="";
            String password="";
            boolean status = false;
            do {
                mainstatus = false;
                boolean usernamestatus=false;
                do {
                System.out.println("Enter username");
                 username = scan.next();
                    if(username.length()<=3)
                    {
                        System.out.println("Username should be min 4 characters");
                        usernamestatus=true;
                    }
                    else
                    {
                        usernamestatus=false;
                    }
                }while(usernamestatus);
                boolean passwordstatus=false;
                do {
                System.out.println("Enter Password");
                 password = scan.next();
                    if(password.length()<=3)
                    {
                        System.out.println("password should be min 4 Characters");
                        passwordstatus=true;
                    }
                    else
                    {
                        passwordstatus=false;
                    }
                }while(passwordstatus);
                UserInterface u = new UserImpl();
                String userstatus = u.existingUser(username, password);
                if (userstatus.equalsIgnoreCase("Invalid Username or Password")) {
                    status = true;
                } else {
                    status = false;
                }
            } while (status);
        } else {
            System.out.println("Wrong Choice");
            mainstatus = true;
        }
    }while(mainstatus);
    }
}
