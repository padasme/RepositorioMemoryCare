package cl.persistencia;

import cl.dominio.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    private Connection connection;

    public GrupoDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void agregar(Grupo grupo){
        String sql = "insert into grupo (descripcion) values (?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, grupo.getDescripcion());            
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Error al insertar nuevo grupo, ",e);
        }
    }
    
    public Grupo buscarPorDescripcion(String descripcion){
        String sql="select * from grupo where descripcion = ? ";
        Grupo grupo = null;
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, descripcion);                        
            try(ResultSet rs=stmt.executeQuery()){
                if (rs.next()) {
                    grupo=new Grupo();
                    grupo.setId(rs.getInt("id"));
                    grupo.setDescripcion(rs.getString("descripcion"));                    
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al buscar grupo por descripción, ",e);
        }
        return grupo;
    }
    
    public void modificar(Grupo grupo){
        String sql="update grupo set descripcion=? where id=? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,grupo.getDescripcion());            
            stmt.setInt(2,grupo.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Error: imposible actualizar descripcion del grupo, ",e);
        }
    }
    
    public List<Grupo> buscarTodos(){
        List<Grupo> lista = new ArrayList<>();        
        String sql = "select * " +
                     "from grupo " +
                     "order by descripcion ASC";        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                     
            ResultSet rs = stmt.executeQuery();){
                
                while (rs.next()) {
                    Grupo grupo = new Grupo();
                    grupo.setId(rs.getInt("id"));
                    grupo.setDescripcion(rs.getString("descripcion"));                   
                    lista.add(grupo);
            }
        } catch (SQLException e) {            
            throw new RuntimeException ("Error al desplegar grupos",e);
        }
        return lista;
    }
    
    public List<Grupo> buscarPorId(int rut){
        List<Grupo> lista = new ArrayList<>();        
        String sql = "select * " +
                     "from grupo " +
                     "Where id in(Select grupo_id from usuario_grupo where ficha_rut= ?) " +
                     "order by descripcion ASC";        
        try (PreparedStatement stmt = connection.prepareStatement(sql);                     
            ){
            stmt.setInt(1,rut);
            ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Grupo grupo = new Grupo();
                    grupo.setId(rs.getInt("id"));
                    grupo.setDescripcion(rs.getString("descripcion"));                   
                    lista.add(grupo);
            }
        } catch (SQLException e) {            
            throw new RuntimeException ("Error al desplegar grupos del usuario",e);
        }
        return lista;
    }
}
