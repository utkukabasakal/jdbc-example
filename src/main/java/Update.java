import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = dbHelper.getConnection();
            //statement = connection.createStatement();
            //statement.executeUpdate("update isim set isim = 'yunus' where id = 3");
            String sql = "update person set isim = 'yunus' where id = 3";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Kayit guncellendi.");

        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }
}
