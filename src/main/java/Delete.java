import java.sql.*;

public class Delete {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = dbHelper.getConnection();
            String sql = "delete from person where id = 5";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Kayit silindi.");

        } catch (SQLException exception) {
            dbHelper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
    }
}
