package cl.persistencia;

import cl.dominio.Antecedente;
import cl.dominio.Tipo_antecedente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AntecedenteDAO {

    private Connection connection;

    public AntecedenteDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void agregar(Antecedente antecedente){
        String sql = "insert into antecedente (descripcion, tipo_antecedente_id, estado) values (?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, antecedente.getDescripcion());
            stmt.setInt(2, antecedente.getTipo_antecedente_id());            
            stmt.setString(3, String.valueOf(antecedente.getEstado())); 
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Error al insertar antecedente, ",e);
        }
    }
    
    public Antecedente buscarPorDescripcion(String descripcion, int tipo_antecedente_id){
        String sql="select * from antecedente where descripcion = ? and tipo_antecedente_id= ?";
        Antecedente antecedente = null;
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, descripcion);            
            stmt.setInt(2,tipo_antecedente_id);
            try(ResultSet rs=stmt.executeQuery()){
                if (rs.next()) {
                    antecedente=new Antecedente();
                    antecedente.setId(rs.getInt("id"));
                    antecedente.setDescripcion(rs.getString("descripcion"));
                    antecedente.setTipo_antecedente_id(rs.getInt("tipo_antecedente_id"));
                    antecedente.setEstado(rs.getString("estado").charAt(0));
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al buscar por grupo por descripcion, ",e);
        }
        return antecedente;
    }
    
    public void actualizarEstado(Antecedente antecedente){
        String sql="update antecedente set estado=? where id=? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,String.valueOf(antecedente.getEstado()));            
            stmt.setInt(2,antecedente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: imposible actualizar estado del antecedente, ",e);
        }
    }
    
    public void modificar(Antecedente antecedente){
        String sql="update antecedente set descripcion=? where id=? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,antecedente.getDescripcion());            
            stmt.setInt(2,antecedente.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Error: imposible actualizar descripcion del antecedente, ",e);
        }
    }
    
    public List<Antecedente> buscarPorId(int id, char estado){
        List<Antecedente> lista = new ArrayList<>();
        
        String sql = "select a.* " +
                     "from antecedente a " +
                     "inner join " +
                     "tipo_antecedente b " +
                     "on a.tipo_antecedente_id=b.id " +
                     "where a.tipo_antecedente_id=? " +
                     "And a.estado=? "+ 
                     "order by a.id ASC";
        
        try (
                PreparedStatement stmt = connection.prepareStatement(sql);                     
            ){
            stmt.setInt(1, id);    
            stmt.setString(2, String.valueOf(estado));
                try (
                    ResultSet rs = stmt.executeQuery())
                {
                    while (rs.next()) {
                        Antecedente antecedente = new Antecedente();
                        antecedente.setId(rs.getInt("id"));
                        antecedente.setDescripcion(rs.getString("descripcion"));
                        antecedente.setTipo_antecedente_id(rs.getInt("tipo_antecedente_id"));
                        antecedente.setEstado(rs.getString("estado").charAt(0));
                        lista.add(antecedente);
                    }                
                }
        } catch (SQLException e) {            
            throw new RuntimeException ("Error al desplegar Antecedentes",e);
        }
        return lista;
    }
    
    
}
