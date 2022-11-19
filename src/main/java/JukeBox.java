import DAOImplClasses.UserImpl;

import java.util.Scanner;

public class JukeBox
{
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Welcome to Jukebox");
        System.out.println("1.New user\n2.Existing User");
        int userchoice=scan.nextInt();
        if(userchoice==1)
        {
            System.out.println("Enter username");
            String username=scan.next();
            System.out.println("Enter Password");
            String password=scan.next();
            UserImpl u=new UserImpl();
            u.createNewUser(username,password);
        }

    }
}
