package cl.servicio;

import cl.dominio.Usuario;
import cl.persistencia.UsuarioDAO;
import java.sql.Connection;

public class UsuarioService {

    UsuarioDAO usuarioDAO;

    public UsuarioService(Connection cnx) {
        usuarioDAO = new UsuarioDAO(cnx);
    }

    public Usuario buscarUsuario(int rut, String dv_rut, String password) {
        return usuarioDAO.buscarUsuario(rut, dv_rut, password);
    }
}
