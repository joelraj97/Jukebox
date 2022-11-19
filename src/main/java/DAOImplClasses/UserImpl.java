package DAOImplClasses;
import DAOInterfaces.UserInterface;
import java.sql.Connection;

public class UserImpl implements UserInterface
{

    @Override
    public void createNewUser(String username, String password)
    {
       try
       {
           Connection con=getConnection();
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
    }

    @Override
    public void existingUser(String username, String password)
    {

    }
}
