package modelo;

import core.BDAbstract;
import modelo.Usuario;

/**
 *
 * @author Fabian
 */
public class DAOUsuario extends BDAbstract {

    @Override
    public boolean insertar(Object obj) {
        Usuario usuario = (Usuario) obj;
        try {
            sentencia = "insert into usuarios values (?,?,?,?)";
            ps = crearConexion().prepareStatement(sentencia);
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getUsername());
            ejecutarSentencia();
            if (filas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error al insertar");
        }
        return false;
    }

    @Override
    public Usuario seleccionar(Object obj) {
        Usuario usuario = (Usuario) obj;
        try {
            sentencia = "select nombres, apellidos from usuarios where username=? and password=?";
            ps = crearConexion().prepareStatement(sentencia);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            rs = ejecutarSelect();
            while (rs.next()) {
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
            }
        } catch (Exception e) {
        }
        return usuario;
    }

}
