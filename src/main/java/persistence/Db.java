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
            p.load(in);
            URL  = p.getProperty("db.url");
            USER = p.getProperty("db.user");
            PASS = p.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar db.properties", e);
        }
    }


    // Metodo para ejecutar una consulta SELECT y retornar un ResultSet
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // fuerza el registro del driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver MySQL en el classpath", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeQuery();
        } catch (SQLException e) {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
            throw e; // Relanza para que el caller maneje
        }
    }
    // Metodo para ejecutar un INSERT/UPDATE/DELETE
    public static int executeUpdate(String query, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate();
        } finally {
            // Siempre cierra recursos
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
