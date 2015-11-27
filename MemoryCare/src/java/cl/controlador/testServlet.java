/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Antecedente;
import cl.dominio.Grupo;
import cl.dominio.Tipo_antecedente;
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

/**
 *
 * @author Roman
 */
@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet {
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
        
        try (Connection cnx = ds.getConnection()){
            Grupo grupo=new Grupo();            
            SistemaService service = new SistemaService(cnx);
            Map<String, String> mapMensajes = new HashMap<>();
            String mensaje=""; 
            
            
            if (request.getSession().getAttribute("rut") != null) {
                int rut=(Integer)request.getSession().getAttribute("rut");
                request.setAttribute("lstGrupos", service.ListarGrupoPorId(rut));
            }
            if (request.getParameter("grupo")!=null) {
                grupo.setId(Integer.valueOf(request.getParameter("grupo")));
                request.setAttribute("grupo", grupo);
            }
            request.getRequestDispatcher("/QR_page.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
