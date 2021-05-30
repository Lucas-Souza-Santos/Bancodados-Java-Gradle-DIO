package parte2;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC
{
    public static void main(String[] args) throws SQLException
    {
        
        String urlConnection = "jdbc:mysql://localhost/digital_innovation_one";

        Connection conn = null;

        try 
        {
            conn = DriverManager.getConnection(urlConnection, "root", "12345");
            System.out.println("SUCESSO!!!");
        }catch (Exception e)
        {
            System.out.println("FALHA!!!");
        }finally {
            conn.close();
        }
    }
}
