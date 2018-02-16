package core;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */
public abstract class BDAbstract {

    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/chat";
    private final String usuario = "root";
    private final String password = "";
    private Connection conn;

    protected String sentencia;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected int filas;

    abstract protected boolean insertar(Object obj);

    abstract protected Object seleccionar(Object obj);

    /**
     * Ejecuta una sentencia SQL Preparada
     *
     *
     */
    protected void ejecutarSentencia() {
        try {
            filas = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {

        }
    }

    protected ResultSet ejecutarSelect() {
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            
        }
        return rs;
    }

    /**
     * Crea la conexion ala base de datos y la retorna
     *
     * @return Connection conn
     */
    protected Connection crearConexion() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creando conexion : " + e);
        }
        return conn;
    }

}
