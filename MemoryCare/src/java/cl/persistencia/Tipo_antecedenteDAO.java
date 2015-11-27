package cl.persistencia;

import cl.dominio.Tipo_antecedente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tipo_antecedenteDAO {

    private Connection connection;

    public Tipo_antecedenteDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Tipo_antecedente> buscarTodos(){
        List<Tipo_antecedente> lista = new ArrayList<>();
        String sql = "select * from tipo_antecedente";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                     
            ResultSet rs = stmt.executeQuery();){
                
                while (rs.next()) {
                    Tipo_antecedente tipo_atencedente = new Tipo_antecedente();
                    tipo_atencedente.setId(rs.getInt("id"));
                    tipo_atencedente.setDescripcion(rs.getString("descripcion"));                   
                    lista.add(tipo_atencedente);
            }
        } catch (SQLException e) {            
            throw new RuntimeException ("Error al desplegar tipos de antecedentes",e);
        }
        return lista;
    }
}
