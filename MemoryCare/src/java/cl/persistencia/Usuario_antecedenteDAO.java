package cl.persistencia;

import java.sql.Connection;

public class Usuario_antecedenteDAO {

    private Connection connection;

    public Usuario_antecedenteDAO(Connection connection) {
        this.connection = connection;
    }
}
