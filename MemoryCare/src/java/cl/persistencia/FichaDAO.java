package cl.persistencia;

import cl.dominio.Ficha;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FichaDAO {

    private Connection connection;

    public FichaDAO(Connection connection) {
        this.connection = connection;
    }

   public void agregar(Ficha objFicha) {

        String sql = "insert into ficha"
                + "(rut, dv_rut, nombre, ap_paterno,ap_materno, fecha_nac,sexo,actividad,estado_civil,telefono,direccion,ciudad, peso, estatura)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, objFicha.getRut());
            stmt.setString(2, String.valueOf(objFicha.getDv_rut()));
            stmt.setString(3, objFicha.getNombre());
            stmt.setString(4, objFicha.getAp_paterno());
            stmt.setString(5, objFicha.getAp_materno());
            stmt.setDate(6, objFicha.getFecha_nac());
            stmt.setString(7, String.valueOf(objFicha.getSexo()));
            stmt.setString(8, objFicha.getActividad());
            stmt.setString(9, String.valueOf(objFicha.getEstado_civil()));
            stmt.setString(10, objFicha.getTelefono());
            stmt.setString(11, objFicha.getDireccion());
            stmt.setString(12, objFicha.getCiudad());
            stmt.setInt(13, objFicha.getPeso());
            stmt.setInt(14, objFicha.getEstatura());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al Ingresar Ficha", e);
        }
    }

    public List<Ficha> buscarTodosFichaParaInforme() {
        List<Ficha> lista = new ArrayList<>();
        String sql = "select * from ficha order by ap_paterno,ap_materno";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Ficha ficha = new Ficha();
                ficha.setRut(rs.getInt("rut"));
                ficha.setDv_rut(rs.getString("dv_rut").charAt(0));
                ficha.setNombre(rs.getString("nombre"));
                lista.add(ficha);
            }
        } catch (SQLException e) {

            throw new RuntimeException("Error al buscar Todas las Fichas", e);
        }
        return lista;
    }

    public Ficha buscarFichasEnFichaParaValidar(int rut) {

        
        String sql = "select * from ficha where rut = ? order by ap_paterno";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, rut);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Ficha ficha = new Ficha();
                    ficha.setRut(rs.getInt("rut"));
                    ficha.setDv_rut(rs.getString("dv_rut").charAt(0));
                    ficha.setNombre(rs.getString("nombre"));
                    return ficha;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {

            throw new RuntimeException("Error al buscar en Todas las Fichas", e);
        }
        
    }

}
