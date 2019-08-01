import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertInto {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement  = null;
        ResultSet resultSet;
        try {
            connection = dbHelper.getConnection();
            statement  =connection.prepareStatement( "insert into person(isim,soyisim) values ('furkan','korkmaz')");
            statement.executeUpdate();
            System.out.println("Kayit eklendi.");

        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }
}
