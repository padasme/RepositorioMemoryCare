<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="librerias.jsp" %>
    </head>
    <body>
    <center>
        <form action="testServlet" method="post"/>
        <%@include file="menu_superior.jsp" %>
        <h3>Obtener Código QR</h3></p>
        <!-- Inicio el dropdown list -->
        <div class="col-xs-20 form-group"><!-- prueba de github-->
            <label class="col-xs-5 control-label" style="text-align: right">Mis grupos: </label>
                <div class="col-xs-2 selectContainer">
                    <select class="form-control" name="grupo" onchange="this.form.submit()">
                        <option value="0"> -- Seleccione -- </option>
                        <c:forEach var="r" items="${lstGrupos}">
                        <option value="${r.id}" ${r.id == grupo.id ? 'Selected' : ''}>
                            <c:out value="${r.descripcion}"/>
                        </option>
                        </c:forEach>
                    </select>
                </div>
        </div>
        <!-- Fin el dropdown list -->        
       </form>
    </p>
       <div style="width: 450px; height: 400px !important; overflow-y: scroll;">
        <table class="table table-striped" style="table-layout: fixed; width: 100%">
            <tr  style="display: block; position: absolute;">
            <th style="width: 350px;">Rut</th>
            <th style="width: 350px;">Nombre Usuario</th>
            <th style="width: 84px;text-align:right">Acción</th>
            </tr>
            <tr><td colspan="2">&nbsp;</td></tr>
            <c:forEach var="p" items="${lstUsuarios}">
                <tr>
                    <td style="width: 350px;"><c:out value="${p.rut}"/></td>
                    <td style="width: 350px;"><c:out value="${p.nombre}"/></td>
                    <td style="width: 84px;text-align: right">
                        <form action="<c:url value="/testServlet"/>" method="post">
                            <input type="hidden" name="id" value="${p.id}"/>                            
                            <input type="submit" name="btn" value="Imprimir"/>                            
                        </form>
                    </td>            
                </tr>
            </c:forEach>
        </table></div>
    </center>
    </body>
</html>
