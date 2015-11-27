package cl.controlador;

import cl.dominio.Ficha;
import cl.servicio.ServicioException;
import cl.servicio.SistemaService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet(name = "IngresarFichaServlet", urlPatterns = {"/IngresarFicha"})
public class IngresarFichaServlet extends HttpServlet {


    @Resource(mappedName = "jdbc/memorycare")
    private javax.sql.DataSource ds;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/ingreso_ficha.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

   
        try (Connection connection = ds.getConnection()) {
            SistemaService service = new SistemaService(connection);


            Map<String, String> mapMensajes = new HashMap<>();
            String mensaje = "";

            String rut = request.getParameter("rut");
            String dv = request.getParameter("dv");
            String nombre = request.getParameter("nombre");
            String ap_paterno = request.getParameter("ap_paterno");
            String ap_materno = request.getParameter("ap_materno");
            String fecha_nac = request.getParameter("fecha_nac");
            String sexo = request.getParameter("sexo");
            String actividad = request.getParameter("actividad");
            String estado_civil = request.getParameter("estado_civil");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String ciudad = request.getParameter("ciudad");
            String peso = request.getParameter("peso");
            String estatura = request.getParameter("estatura");

            //validaciones
            if (nombre.isEmpty()) {
                mapMensajes.put("nombre", "Nombre debe ser Ingresado");
            }
            if (ap_paterno.isEmpty()) {
                mapMensajes.put("ap_paterno", "Apellido Paterno debe ser Ingresado");
            }
            if (ap_materno.isEmpty()) {
                mapMensajes.put("ap_materno", "Apellido Materno debe ser Ingresado");
            }

            java.sql.Date sqldate_fecha_nac = null;
            if (fecha_nac.isEmpty()) {
                mapMensajes.put("fecha_nac", "Fecha Nacimiento debe ser Ingresada");
            } else {
                try {
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    Date utildate_fecha_nac = formater.parse(fecha_nac);
                    sqldate_fecha_nac = new java.sql.Date(utildate_fecha_nac.getTime());
                } catch (Exception e) {
                    mapMensajes.put("fecha_nac", "Fecha Nacimiento debe ser una Fecha Valida");
                }
            }

            if (actividad.isEmpty()) {
                mapMensajes.put("actividad", "Actividad debe ser Ingresada");
            }
            if (telefono.isEmpty()) {
                mapMensajes.put("telefono", "Telefono debe ser Ingresado");
            }
            if (direccion.isEmpty()) {
                mapMensajes.put("direccion", "Dirección debe ser Ingresada");
            }
            if (ciudad.isEmpty()) {
                mapMensajes.put("ciudad", "Ciudad debe ser Ingresada");
            }
            if (peso.isEmpty()) {
                mapMensajes.put("peso", "Peso debe ser Ingresado");
            } else {
                try {
                    int pesocomp = Integer.parseInt(peso);
                    if (pesocomp <= 0) {
                        mapMensajes.put("peso", "Peso debe ser Positivo");
                    }
                } catch (NumberFormatException e) {
                    mapMensajes.put("peso", "Peso '" + request.getParameter("peso") + "' no es Válido");
                }
            }
            if (estatura.isEmpty()) {
                mapMensajes.put("estatura", "Estatura debe ser Ingresado");
            } else {
                try {
                    int estaturacomp = Integer.parseInt(estatura);
                    if (estaturacomp <= 30 || estaturacomp > 250) {
                        mapMensajes.put("estatura", "Estatura debe estar entre 30 y 250 CM");
                    }
                } catch (NumberFormatException e) {
                    mapMensajes.put("estatura", "Estarua '" + request.getParameter("estatura") + "' no es Válida");
                }
            }

            //si no existen datos en blanco...
            if (mapMensajes.isEmpty()) {
                //validar RUT
                int cont = 2, suma = 0, digitoCalculado;
                String digitoFinal = "";
                if (rut.isEmpty()) {
                    mapMensajes.put("rut", "Falta Rut");
                }
                if (dv.isEmpty()) {
                    mapMensajes.put("dv", "Falta Digito Verificador");
                }
                try {
                    //13193029-?
                    for (int i = rut.length() - 1; i >= 0; i--) {
                        char c = rut.charAt(i);//'9'
                        String n = String.valueOf(c);//"9"
                        int numero = Integer.parseInt(n) * cont;// 9 * 2
                        suma += numero;
                        cont++;
                        if (cont == 8) {
                            cont = 2;
                        }
                    }
                    digitoCalculado = 11 - suma % 11;
                    if (digitoCalculado == 10) {
                        digitoFinal = "K";
                    } else if (digitoCalculado == 11) {
                        digitoFinal = "0";
                    } else {
                        digitoFinal = String.valueOf(digitoCalculado);
                    }
                    if (!digitoFinal.equalsIgnoreCase(dv)) {
                        mapMensajes.put("dv", "Rut No Valido");
                    }
                    if (digitoFinal.equalsIgnoreCase(dv)) {
                        //busco en la tabla ficha si el rut Existe.
                        if (null == service.buscarFichasEnFichaParaValidarService(Integer.parseInt(rut))) {
                            Ficha ficha;
                            ficha = new Ficha(Integer.parseInt(rut), dv.charAt(0), nombre, ap_paterno, ap_materno, sqldate_fecha_nac, sexo.charAt(0), actividad, estado_civil.charAt(0), telefono, direccion, ciudad, Integer.parseInt(peso), Integer.parseInt(estatura));
                            try {
                                service.agregarFicha(ficha);
                                // ver si redireccionar a pagina con listado de fichas del usuario.-
                                mapMensajes.put("exito", "Ficha Agregada Con Exito");
                                request.setAttribute("mapMensajes", mapMensajes);
                                request.getRequestDispatcher("/index.jsp").forward(request, response);
                            } catch (Exception ex) {
                                // No se pudo ingresar ficha
                                mapMensajes.put("fracaso", "Ficha No Fue Agregada, Existió Un Problema.");
                            }
                        } else {
                            mapMensajes.put("existe", "Ficha No puede Ser Agregada, Ya Existe");
                        }
                    }
                } catch (NumberFormatException e) {
                    mapMensajes.put("rut", "Rut No Valido");
                }
            }

            //recuperamos mensajes
            request.setAttribute("mapMensajes", mapMensajes);

            //para devolver los elementos y que los text no queden vacios al generar errores
            //en las validaciones
            request.setAttribute("rut", rut);
            request.setAttribute("dv", dv);
            request.setAttribute("nombre", nombre);
            request.setAttribute("ap_paterno", ap_paterno);
            request.setAttribute("ap_materno", ap_materno);
            request.setAttribute("fecha_nac", fecha_nac);
            request.setAttribute("sexo", sexo);
            request.setAttribute("actividad", actividad);
            request.setAttribute("estado_civil", estado_civil);
            request.setAttribute("telefono", telefono);
            request.setAttribute("direccion", direccion);
            request.setAttribute("ciudad", ciudad);
            request.setAttribute("peso", peso);
            request.setAttribute("estatura", estatura);
            //dispacher al jsp en caso de detectar errores en ingreso de datos
            request.getRequestDispatcher("/ingreso_ficha.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
