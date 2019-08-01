import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException{
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = dbHelper.getConnection();
            System.out.println("Bağlantı kuruldu");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM person");
            while(resultSet.next()){
                System.out.println(resultSet.getString("id") + "    " +resultSet.getString("isim")+ "   " + resultSet.getString("soyisim"));
            }
        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            connection.close();
        }
    }
}