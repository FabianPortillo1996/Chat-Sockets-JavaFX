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
    
    protected Connection conn;
    protected String sentencia;
    protected PreparedStatement ps;
    
    
    
    abstract protected void insertar(Object obj);
    abstract protected void seleccionar(Object obj);
    
    private Connection crearConexion(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creando conexion : " + e);
        }
        return conn;
    }
    
    private void cerrarConexion(){
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error cerrando conexion : " +e);
        }
    }
    
    protected void ejecutarSentencia(){
        try {
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
