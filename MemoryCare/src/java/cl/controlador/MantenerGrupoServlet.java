package cl.controlador;

import cl.dominio.Grupo;
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

@WebServlet(name = "MantenerGrupoServlet", urlPatterns = {"/Grupos"})
public class MantenerGrupoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/memorycare")
    private DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(Connection connection=ds.getConnection()){
            Grupo grupo=new Grupo();            
            SistemaService service = new SistemaService(connection);
            Map<String, String> mapMensajes = new HashMap<>();
            String mensaje=""; 
            
            if (request.getParameter("btn")!= null && request.getParameter("btn").equals("Agregar")) {
                //valido que exista una descripción a agregar
                if (!request.getParameter("descripcion").isEmpty()) {
                    String descripcion=request.getParameter("descripcion");                    
                    grupo.setDescripcion(descripcion);                    
                }else{
                    mapMensajes.put("descripcion", "Favor, ingresar una descripción para el grupo");
                }
                
                //valido que no existan mensajes de error, y grabo.
                if (mapMensajes.isEmpty()) {                        
                    try {
                        service.agregarGrupo(grupo);
                        mensaje="El grupo ha sido registrado existosamente";
                        request.setAttribute("mensaje", mensaje);                    
                    } catch (ServicioException e) {
                        mensaje = e.getMessage();
                        request.setAttribute("ErrorMensaje", mensaje);                    
                    }                        
                }else{
                    mensaje = "Favor, revise el formulario";
                    request.setAttribute("ErrorMensaje", mensaje);
                }
            }            
            
            if (request.getParameter("btn")!= null && request.getParameter("btn").equals("Editar")) {                    
                grupo.setDescripcion(request.getParameter("descripcion_lista"));                
                grupo.setId(Integer.valueOf(request.getParameter("id")));                
                request.setAttribute("grupo", grupo);                               
                request.getRequestDispatcher("modificar_grupo.jsp").forward(request,response);                            
            }
            
            if (request.getParameter("btn")!= null && request.getParameter("btn").equals("Modificar")) {                
                grupo.setDescripcion(request.getParameter("descripcion"));
                grupo.setId(Integer.valueOf(request.getParameter("id")));
                if (!grupo.getDescripcion().isEmpty()) {                    
                    service.modificarGrupo(grupo);
                    mensaje="Registro modificado existosamente";
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("lstGrupos", service.ListarGrupo());
                    request.getRequestDispatcher("/mantenedor_grupos.jsp").forward(request, response);
                }else{
                    mensaje = "Favor, revise el formulario";
                    mapMensajes.put("descripcion_lista", "Favor, ingrese una descripción para el grupo");
                    request.setAttribute("grupo", grupo);
                    request.setAttribute("ErrorMensaje", mensaje);
                    request.setAttribute("mapMensajes",mapMensajes);                    
                    request.getRequestDispatcher("/modificar_grupo.jsp").forward(request, response);
                }                                
            }
            
            if (request.getParameter("btn")!= null && request.getParameter("btn").equals("Cancelar")) {
                mensaje = "La operación ha sido cancelada";
                request.setAttribute("mensaje", mensaje);
                request.setAttribute("lstGrupos", service.ListarGrupo());
                request.getRequestDispatcher("/mantenedor_grupos.jsp").forward(request, response);
            }            
            request.setAttribute("mapMensajes",mapMensajes);                   
            request.setAttribute("lstGrupos", service.ListarGrupo());
            request.getRequestDispatcher("/mantenedor_grupos.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
