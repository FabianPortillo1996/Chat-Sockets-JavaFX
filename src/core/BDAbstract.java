package core;

import java.sql.*;

/**
 *
 * @author Fabian
 */
public abstract class BDAbstract {
    
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url= "jdbc:mysql://localhost:3306/chat";
    private final String usuario = "root";
    private final String password = "";
    private Connection conn;
    
    protected String sentencia;
    protected PreparedStatement ps;
    
    abstract protected void insertar();
    abstract protected void seleccionar();
    
    private void crearConexion(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creando conexion : " + e);
        }
    }
    
    private void cerrarConexion(){
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error cerrando conexion : " +e);
        }
    }
    private void ejecutarSentencia(){
        try {
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
