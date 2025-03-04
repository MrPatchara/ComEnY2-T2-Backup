package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainFrmApplication {
    private static final String URL = "jdbc:mysql://localhost:3306/POO_projet";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static List<Connection> connectionPool = new ArrayList<>();
    public static List<Contact> repertoire = new ArrayList<>();

    static {
        initializeConnectionPool();
    }

    private static void initializeConnectionPool() {
        for (int i = 0; i < 10; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connectionPool.add(connection);
            } catch (SQLException e) {
                // Gérer l'erreur de manière appropriée
                e.printStackTrace();
            }
        }
    }

    public static synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                // Gérer l'erreur de manière appropriée
                e.printStackTrace();
                return null;
            }
        } else {
            return connectionPool.remove(connectionPool.size() - 1);
        }
    }

    public static synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    public static synchronized void closeAllConnections() {
        for (Connection connection : connectionPool) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Gérer l'erreur de manière appropriée
                e.printStackTrace();
            }
        }
        connectionPool.clear();
    }

    public static void enregistrer() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                for (Contact contact : repertoire) {
                    contact.insererDansBase(connection);
                }
            }
        } catch (SQLException e) {
            // Gérer l'erreur de manière appropriée
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        enregistrer();
        closeAllConnections();
    }
}
