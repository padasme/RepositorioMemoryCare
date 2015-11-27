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
        <form action="<c:url value="/Antecedentes"/>" method="get">
        <%@include file="menu_superior.jsp" %>
            <h3>TIPOS ANTECEDENTES</h3>
            <table>
            <tr>
                <td>
                    Nombre Antecedente:
                </td>
                <td>
                    <select name="tipo_antecedente_id" onchange="this.form.submit()">
                        <option value="0"> -- Seleccione -- </option>                        
                        <c:forEach var="r" items="${lstTipoAntecente}">
                        <option value="${r.id}" ${r.id == antecedente.tipo_antecedente_id ? 'Selected' : ''}>
                            <c:out value="${r.descripcion}"/>
                        </option>
                        </c:forEach>                       
                    </select>                      
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td>Estado:</td>
                <td><input type="radio" name="opcionEstado" value="A" checked="checked" onchange="this.form.submit()" ${'A'==antecedente.estado ? 'Checked': '' } />Activo
                    <input type="radio" name="opcionEstado" value="E" onchange="this.form.submit()" ${'E'==antecedente.estado ? 'Checked': '' } />Inactivo                     
                    &nbsp;
                </td>
            </tr>            
        </table>   
        </form>
    </p>
    <div style="width: 450px; height: 400px !important; overflow-y: scroll;">
        <table class="table table-striped" style="table-layout: fixed; width: 100%">
    <tr  style="display: block; position: absolute;">
        <th style="width: 350px;">Descripción</th>
        <th style="width: 84px;text-align:right">Acción</th>
    </tr>
    <tr><td colspan="2">&nbsp;</td></tr>
    <c:forEach var="p" items="${lstAntecedente}">
        <tr>
            <td style="width: 350px;"><c:out value="${p.descripcion}"/></td>            
            <td style="width: 84px;text-align: right">
                <form action="<c:url value="/Antecedentes"/>" method="post">
                    <input type="hidden" name="id" value="${p.id}"/>                    
                    <input type="hidden" name="descripcion_lista" value="${p.descripcion}"/>
                    <input type="hidden" name="act_tipo_antecedente_id" value="${p.tipo_antecedente_id}"/>
                    <input type="submit" name="btn" value="Editar"/>
                    <input type="submit" name="btn" value="${'A'==antecedente.estado ? 'Inactivar' : 'Activar'}"/>
                </form>
            </td>            
        </tr>
    </c:forEach>
</table></div>
    
    </p>
    <table>
        <tr>
            <td>Descripción:</td>
            <td>                
                <form action="<c:url value="/Antecedentes"/>" method="get">
                    <input type="hidden" name="ing_estado" value="${antecedente.estado}"/>                    
                    <input type="hidden" name="ing_tipo_antecedente_id" value="${antecedente.tipo_antecedente_id}"/>
                    <input type="text" name="descripcion" value="<c:out value="${antecedente.descripcion}"/>"/>
                    <input type="submit" name="btn" value="Agregar"/>                              
                </form>
            </td>
        </tr>
    </table>
    </center> 
                
    <script type="text/javascript">
    $(document).ready(function () {
        <c:set var = "tipo_antecedente_id" value = "${mapMensajes['tipo_antecedente_id']}" />
        <c:if test = "${not empty tipo_antecedente_id}" >
            toastr.error("<c:out value="${tipo_antecedente_id}" />");
        </c:if>

        <c:set var = "descripcion" value = "${mapMensajes['descripcion']}" />
        <c:if test = "${not empty descripcion}" >
            toastr.error("<c:out value="${descripcion}" />");
        </c:if>                        
        
        <c:set var="ErrorMensaje" value="${ErrorMensaje}"/>
        <c:if test ="${not empty ErrorMensaje}">
            toastr.error("<c:out value="${ErrorMensaje}"/>");    
        </c:if>
        
        <c:set var="mensaje" value="${mensaje}"/>
        <c:if test ="${not empty mensaje}">
            toastr.success("<c:out value="${mensaje}"/>");    
        </c:if>                            
    });
</script> 

    </body>
</html>