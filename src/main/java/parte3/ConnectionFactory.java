package parte3;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory 
{
    // Construtor declarado como privado. Evvvitando assim criar instância da fabrica
   private ConnectionFactory(){
        throw new UnsupportedOperationException();
   } 

    public static Connection getConnection()
    {
        // OBS: NÃO ESQUECER DE BAIXAR O DRIVER PARA O BANCO DE DADOS QUE IRÁ UTILIZAR

        //1. dECLARAR OBJETO CONEXÃO IRÁ RECEBER UMA CONEXÃO APÓS EXECUÇAO DOS PASSOS ABAIXOS

        Connection connection = null;

        // 2. Carregar arquivos de propriedades para pegar parâmetros necessários para se conectar ccom o banco de dados

        try(InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties"))
        {
            // 3. Definir parammetros para se conectar ao banco de dados 
            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

            // 4. Construindo a string de conexao
            StringBuilder sb = new StringBuilder("jdbc:").append(driver).append("://")
                                                .append(dataBaseAddress).append("/").append(dataBaseName);
            
            String connectionUrl = sb.toString();

            // 5. Criar uma conexao passnado o drivermaneger, passando ccomo parametro a string de connection, o usuario e password
            try {
                connection = DriverManager.getConnection(connectionUrl, user, password);
            } catch (SQLException e) {
                System.out.println("Falha ao tentar criar connection");
                throw new RuntimeException(e);
            }


             
        }
        catch(IOException e) 
        {
            System.out.println("Falha ao tentar carregar arquivo de connection");
            e.printStackTrace();

        }
 
        return connection;
   }
}
