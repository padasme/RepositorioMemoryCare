package cl.persistencia;

import java.sql.Connection;

public class Ficha_usuarioDAO {

    private Connection connection;

    public Ficha_usuarioDAO(Connection connection) {
        this.connection = connection;
    }
}
