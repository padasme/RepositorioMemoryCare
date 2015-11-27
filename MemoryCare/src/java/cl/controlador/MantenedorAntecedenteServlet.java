/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Antecedente;
import cl.dominio.Tipo_antecedente;
import cl.servicio.ServicioException;
import cl.servicio.SistemaService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "MantenedorAntecedenteServlet", urlPatterns = {"/Antecedentes"})
public class MantenedorAntecedenteServlet extends HttpServlet {
    @Resource(mappedName = "jdbc/memorycare")
    private DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Aquí manejo la carga inicial, consultas, e ingreso de datos en cada campo del formulario. También se registra una nueva descripción y valida.
        try (Connection cnx = ds.getConnection()){
            Antecedente antecedente=new Antecedente();
            Tipo_antecedente tipo_antecedente = new Tipo_antecedente();
            SistemaService service = new SistemaService(cnx);
            Map<String, String> mapMensajes = new HashMap<>();
            String mensaje="";        
            
            char opcionEstado=String.valueOf(request.getParameter("opcionEstado")).charAt(0);
            antecedente.setEstado(opcionEstado);
            if (request.getParameter("tipo_antecedente_id")!= null) {
                int tipo_antecedente_id=Integer.valueOf(request.getParameter("tipo_antecedente_id"));                
                antecedente.setTipo_antecedente_id(tipo_antecedente_id);
                request.setAttribute("antecedente", antecedente);
                request.setAttribute("tipo_antecedente", tipo_antecedente);
                request.setAttribute("lstAntecedente",service.ListarAntecedente(tipo_antecedente_id, opcionEstado));
            }
            
            if (request.getParameter("btn")!= null && request.getParameter("btn").equals("Agregar")) {
                String btn=request.getParameter("btn");                
                //valido que los campos necesarios esten seleccionados y con información.                
                if (request.getParameter("ing_tipo_antecedente_id") == null || request.getParameter("ing_tipo_antecedente_id").isEmpty()){
                    mapMensajes.put("tipo_antecedente_id", "Favor, seleccionar un tipo de antecedente");
                }else{
                    if (request.getParameter("ing_tipo_antecedente_id").equals("0")) {
                        mapMensajes.put("tipo_antecedente_id", "Favor, seleccionar un tipo de antecedente");
                    }else{
                        antecedente.setTipo_antecedente_id(Integer.valueOf(request.getParameter("ing_tipo_antecedente_id")));
                        antecedente.setEstado(String.valueOf(request.getParameter("ing_estado")).charAt(0));
                        request.setAttribute("lstAntecedente",service.ListarAntecedente(antecedente.getTipo_antecedente_id(), antecedente.getEstado()));
                    }
                }                                
                
                if (btn.equals("Agregar")) {
                    //valido que exista una descripción a agregar
                    if (!request.getParameter("descripcion").isEmpty()) {
                    String descripcion=request.getParameter("descripcion");                    
                        antecedente.setDescripcion(descripcion);
                    }else{
                        mapMensajes.put("descripcion", "Favor, ingresar una descripción para el antecedente");
                    }

                    //valido que no existan mensajes de error, y grabo.
                    if (mapMensajes.isEmpty()) {                        
                        try {
                            service.agregarAntecedente(antecedente);
                            mensaje="El antecedente ha sido registrado existosamente";
                            request.setAttribute("mensaje", mensaje);                    
                        } catch (ServicioException e) {
                            mensaje = e.getMessage();
                            request.setAttribute("ErrorMensaje", mensaje);                    
                        }                        
                        request.setAttribute("lstAntecedente",service.ListarAntecedente(antecedente.getTipo_antecedente_id(), antecedente.getEstado()));                    
                    }else{
                        mensaje = "Favor, revise el formulario";
                        request.setAttribute("ErrorMensaje", mensaje);
                    }
                }                                
                request.setAttribute("antecedente", antecedente);                
            }
            request.setAttribute("mapMensajes",mapMensajes);       
            request.setAttribute("lstTipoAntecente",service.ListarTipoAntecedente());            
            request.getRequestDispatcher("/mantenedor_antecedentes.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Aquí manejo las operaciones correspondientes a los registros listados en la tabla, ya sea inactivar, activar, modificar.
        try (Connection cnx = ds.getConnection()){
            Antecedente antecedente=new Antecedente();        
            Tipo_antecedente tipo_antecedente = new Tipo_antecedente();
            SistemaService service = new SistemaService(cnx);
            Map<String, String> mapMensajes = new HashMap<>();
            String mensaje="";        
            String btn=request.getParameter("btn");                        
            
            if (request.getParameter("act_tipo_antecedente_id")!=null) {
                antecedente.setTipo_antecedente_id(Integer.valueOf(request.getParameter("act_tipo_antecedente_id")));
            }
            
            if (btn.equals("Activar")) {                
                antecedente.setId(Integer.valueOf(request.getParameter("id")));                        
                antecedente.setEstado('A');
                service.cambioEstado(antecedente);
                mensaje="Registro activado existosamente";                                  
                antecedente.setEstado('E');
                request.setAttribute("mensaje", mensaje);            
                request.setAttribute("antecedente", antecedente);            
                request.setAttribute("lstAntecedente",service.ListarAntecedente(antecedente.getTipo_antecedente_id(), antecedente.getEstado()));
                request.setAttribute("lstTipoAntecente",service.ListarTipoAntecedente());                        
                request.getRequestDispatcher("/mantenedor_antecedentes.jsp").forward(request, response);            
            }                
            
            if (btn.equals("Inactivar")) {                                        
                antecedente.setId(Integer.valueOf(request.getParameter("id")));                
                antecedente.setEstado('E');                    
                service.cambioEstado(antecedente);
                mensaje="Registro inactivado existosamente";                               
                antecedente.setEstado('A');                
                request.setAttribute("mensaje", mensaje);            
                request.setAttribute("antecedente", antecedente);            
                request.setAttribute("lstAntecedente",service.ListarAntecedente(antecedente.getTipo_antecedente_id(), antecedente.getEstado()));
                request.setAttribute("lstTipoAntecente",service.ListarTipoAntecedente());                        
                request.getRequestDispatcher("/mantenedor_antecedentes.jsp").forward(request, response);            
            }
            
            if (btn.equals("Editar")) {                    
                antecedente.setDescripcion(request.getParameter("descripcion_lista"));                
                antecedente.setId(Integer.valueOf(request.getParameter("id")));                
                request.setAttribute("antecedente", antecedente);                               
                request.getRequestDispatcher("modificar_antecedente.jsp").forward(request,response);                            
            }
            
            if (btn.equals("Modificar")) {
                antecedente.setDescripcion(request.getParameter("descripcion"));
                antecedente.setId(Integer.valueOf(request.getParameter("id")));
                if (!antecedente.getDescripcion().isEmpty()) {                    
                    service.modificarAntecedente(antecedente);
                    mensaje="Registro modificado existosamente";
                    request.setAttribute("mensaje", mensaje);
                    doGet(request,response);
                }else{
                    mensaje = "Favor, revise el formulario";
                    mapMensajes.put("descripcion_lista", "Favor, ingrese una descripción para el antecedente");
                    request.setAttribute("antecedente", antecedente);
                    request.setAttribute("ErrorMensaje", mensaje);
                    request.setAttribute("mapMensajes",mapMensajes);                    
                    request.getRequestDispatcher("/modificar_antecedente.jsp").forward(request, response);
                }                                
            }
            
            if (btn.equals("Cancelar")) {
                mensaje = "La operación ha sido cancelada";
                request.setAttribute("mensaje", mensaje);
                doGet(request,response);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
    }
}
