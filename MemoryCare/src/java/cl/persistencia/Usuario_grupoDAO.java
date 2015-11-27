package cl.persistencia;

import cl.dominio.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario_grupoDAO {

    private Connection connection;

    public Usuario_grupoDAO(Connection connection) {
        this.connection = connection;
    }
    
    
}
