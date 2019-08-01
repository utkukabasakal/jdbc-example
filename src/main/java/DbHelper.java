import java.sql.DriverManager;
import  java.sql.Connection;
import java.sql.SQLException;

public class DbHelper {
    private String userName="utku";
    private String password="utku5858";
    private String dbUrl="jdbc:mysql://localhost:3306/crud?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(dbUrl,userName,password);
    }
    public void showErrorMessage(SQLException exception){
        System.err.println("Error : " +exception.getMessage());
        System.err.println("Error code : " + exception.getMessage());
    }
}
