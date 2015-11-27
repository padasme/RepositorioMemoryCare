package cl.servicio;

import cl.dominio.Antecedente;
import cl.dominio.Ficha;
import cl.dominio.Grupo;
import cl.dominio.Tipo_antecedente;
import cl.persistencia.AntecedenteDAO;
import cl.persistencia.FichaDAO;
import cl.persistencia.GrupoDAO;
import cl.persistencia.Tipo_antecedenteDAO;
import java.sql.Connection;
import java.util.List;

public class SistemaService {
    private FichaDAO fichaDAO;
    private AntecedenteDAO antecedenteDAO;
    private Tipo_antecedenteDAO tipo_antecedenteDAO;
    private GrupoDAO grupoDAO;
    
    public SistemaService (Connection connection){
    
        fichaDAO = new FichaDAO(connection);
        antecedenteDAO=new AntecedenteDAO(connection);
        tipo_antecedenteDAO= new Tipo_antecedenteDAO(connection);
        grupoDAO= new GrupoDAO(connection);    
    }
    
    public void agregarFicha(Ficha objFicha){    
        fichaDAO.agregar(objFicha);        
    }

    public Ficha buscarFichasEnFichaParaValidarService (int rut){    
        return fichaDAO.buscarFichasEnFichaParaValidar(rut);        
    }
    
    public List<Tipo_antecedente> ListarTipoAntecedente(){
        return tipo_antecedenteDAO.buscarTodos();
    }
    
    public List<Antecedente> ListarAntecedente(int id, char estado){
        return antecedenteDAO.buscarPorId(id, estado);
    }
    
    public void agregarAntecedente(Antecedente nuevoAntecedente) throws ServicioException{        
        Antecedente bd=antecedenteDAO.buscarPorDescripcion(nuevoAntecedente.getDescripcion(),nuevoAntecedente.getTipo_antecedente_id());
        if (bd != null) {
            throw new ServicioException("Ya existe antecedente con la descripción: "+nuevoAntecedente.getDescripcion());
        }
        antecedenteDAO.agregar(nuevoAntecedente);
    }
     
    public void cambioEstado(Antecedente antecedente){
        antecedenteDAO.actualizarEstado(antecedente);
    }
    
    public void modificarAntecedente(Antecedente antecedente){
        antecedenteDAO.modificar(antecedente);
    }
    
    public List<Grupo> ListarGrupoPorId(int rut){
        return grupoDAO.buscarPorId(rut);
    }
    
    public List<Grupo> ListarGrupo(){
        return grupoDAO.buscarTodos();
    }   
    
    public void agregarGrupo(Grupo nuevoGrupo) throws ServicioException{        
        Grupo bd=grupoDAO.buscarPorDescripcion(nuevoGrupo.getDescripcion());
        if (bd != null) {
            throw new ServicioException("Ya existe antecedente con la descripción: "+nuevoGrupo.getDescripcion());
        }
        grupoDAO.agregar(nuevoGrupo);
    }
        
    public void modificarGrupo(Grupo grupo){
        grupoDAO.modificar(grupo);
    }
}
