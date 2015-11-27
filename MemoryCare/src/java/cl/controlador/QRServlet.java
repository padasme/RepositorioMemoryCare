package cl.controlador;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

@WebServlet(name = "QRServlet", urlPatterns = {"/CodigoQR"})
public class QRServlet extends HttpServlet {
    
   private static final long serialVersionUID = 1L;
    public QRServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {                            
        doPost(request, response);        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String qrtext = request.getParameter("qrtext");
        ByteArrayOutputStream out = QRCode.from(qrtext).to(ImageType.PNG).stream();      
        response.setContentType("image/jpg");
        response.setContentLength(out.size());
        OutputStream outStream = response.getOutputStream();
        outStream.write(out.toByteArray());
        outStream.flush();
        outStream.close();              
    }
}