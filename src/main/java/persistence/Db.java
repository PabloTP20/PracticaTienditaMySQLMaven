package persistence;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Db {
    private static String URL;
    private static String USER;
    private static String PASS;

    static {
        try (InputStream in = Db.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties();
            if (in == null) {
                throw new RuntimeException("No se encontró el archivo db.properties en resources/");
            }
            p.load(in);
            URL  = p.getProperty("db.url");
            USER = p.getProperty("db.user");
            PASS = p.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException("❌ No se pudo cargar db.properties", e);
        }
    }

    // ✅ Obtener conexión
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // fuerza el registro del driver MySQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ No se encontró el driver MySQL en el classpath", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ✅ Ejecutar consultas SELECT
    public static ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        return stmt.executeQuery(); // No cerramos aquí para que el caller procese el ResultSet
    }

    // ✅ Ejecutar INSERT / UPDATE / DELETE
    public static int executeUpdate(String query, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();
        }
    }
}

