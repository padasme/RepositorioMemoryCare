package cl.persistencia;

import cl.dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Usuario buscarUsuario(int rut, String dv_rut, String password) {
        Usuario usuario = new Usuario(); //Usuario que retorna el metodo
        
        String query = "select rut, email, perfil from usuario "
                + "where estado = 'A' "
                + "and rut = ? "
                + "and password = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            //Parametros ingresador en la query
            stmt.setInt(1, rut);            
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario.setRut(rs.getInt("rut"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPerfil(rs.getString("perfil").charAt(0));
                }
                else
                {
                    return null; //No se encontró al usuario
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario", e);
        }
        return usuario;
    }
}
