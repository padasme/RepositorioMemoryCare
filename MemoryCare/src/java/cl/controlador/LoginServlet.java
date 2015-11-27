package cl.controlador;

import cl.dominio.Usuario;
import cl.servicio.UsuarioService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/memorycare")
    private DataSource ds;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection connection = ds.getConnection()){
         
            Map<String, String> mapMensajes = new HashMap<>();
            String rut = request.getParameter("txt_rut");
            String dv_rut = request.getParameter("txt_dv_rut");
            String password = request.getParameter("txt_password");

            //Chequea si se ingreso un rut
            if (rut.equals("")) {
                mapMensajes.put("txt_rut", "Ingrese un rut");
            }
            //Chequea si se ingreso un rut
            if (dv_rut.equals("")) {
                mapMensajes.put("txt_rut", "Ingrese un rut");
            }
            //Chequea si se ingreso una contraseña
            if (password.equals("")) {
                mapMensajes.put("txt_password", "Ingrese una password");
            }

            //Chequea si el rut ingresado es solo numerico
            try {
                Integer.parseInt(rut);
            } catch (Exception e) {
            }

            //Si no hay errores hasta este punto...
            if (mapMensajes.isEmpty()) {
                UsuarioService usuarioService = new UsuarioService(connection);
                //Consulta a la base de datos si el rut, el dv y la password coinciden con un usuario
                Usuario usuario = usuarioService.buscarUsuario(Integer.parseInt(rut), dv_rut, password);
                //Si los datos de inicio de sesion son correctos, almacena las variables
                if (null != usuario) {
                    HttpSession session = (HttpSession) request.getSession();
                    
                    session.setAttribute("rut", usuario.getRut());
                    session.setAttribute("email", usuario.getEmail());
                    session.setAttribute("perfil", usuario.getPerfil());
                    request.getRequestDispatcher("/index.jsp").forward(request, response); //Salida con exito
                } else {
                    mapMensajes.put("login_invalido", "Rut o password incorrectos o no existen");
                }

            }
            request.setAttribute("mapMensajes", mapMensajes);
            
            //Si no hay errores hasta este punto...      
            if (mapMensajes.isEmpty()) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            
        } catch (SQLException ex) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
