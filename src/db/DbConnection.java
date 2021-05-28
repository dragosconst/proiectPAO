package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/proiect_pao";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "123456";
    private final Connection connection;

    private DbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = getConnection();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("eroare la conectarea db");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    private static final class SINGLETON_HOLDER {
        private static final DbConnection INSTANCE = new DbConnection();
    }

    public static DbConnection getInstance() {
        return SINGLETON_HOLDER.INSTANCE;
    }

    public Connection getDbConnection() {
        return this.connection;
    }
}
